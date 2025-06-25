import React, { useState, useEffect } from 'react';
import {
    Box,
    Container,
    Paper,
    Tabs,
    Tab,
    Typography,
    Grid,
    Card,
    CardContent,
    CardHeader,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableRow,
    CircularProgress,
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

function Reports() {
    const [activeTab, setActiveTab] = useState(0);
    const [projectHealth, setProjectHealth] = useState(null);
    const [resourceUtilization, setResourceUtilization] = useState(null);
    const [timesheetSummary, setTimesheetSummary] = useState(null);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();
    const token = useSelector((state) => state.auth.token);

    const fetchReports = async () => {
        try {
            const [projectHealthRes, resourceUtilRes, timesheetRes] = await Promise.all([
                fetch('http://localhost:8090/api/reports/project-health', {
                    headers: { 'Authorization': `Bearer ${token}` }
                }),
                fetch('http://localhost:8090/api/reports/resource-utilization', {
                    headers: { 'Authorization': `Bearer ${token}` }
                }),
                fetch('http://localhost:8090/api/reports/timesheet-summary', {
                    headers: { 'Authorization': `Bearer ${token}` }
                })
            ]);

            if (projectHealthRes.ok && resourceUtilRes.ok && timesheetRes.ok) {
                const [healthData, resourceData, timesheetData] = await Promise.all([
                    projectHealthRes.json(),
                    resourceUtilRes.json(),
                    timesheetRes.json()
                ]);
                setProjectHealth(healthData);
                setResourceUtilization(resourceData);
                setTimesheetSummary(timesheetData);
            }
        } catch (error) {
            console.error('Error fetching reports:', error);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchReports();
    }, []);

    const handleTabChange = (event, newValue) => {
        setActiveTab(newValue);
    };

    if (loading) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
                <CircularProgress />
            </Box>
        );
    }

    return (
        <Container>
            <Box sx={{ my: 4 }}>
                <Typography variant="h4" gutterBottom>
                    Reports Dashboard
                </Typography>
                <Tabs value={activeTab} onChange={handleTabChange} sx={{ mb: 3 }}>
                    <Tab label="Project Health" />
                    <Tab label="Resource Utilization" />
                    <Tab label="Timesheet Summary" />
                </Tabs>

                {activeTab === 0 && (
                    <Grid container spacing={3}>
                        <Grid item xs={12} md={4}>
                            <Card>
                                <CardHeader title="Total Projects" />
                                <CardContent>
                                    <Typography variant="h4">
                                        {projectHealth?.totalProjects || 0}
                                    </Typography>
                                </CardContent>
                            </Card>
                        </Grid>
                        <Grid item xs={12} md={4}>
                            <Card>
                                <CardHeader title="Completed Projects" />
                                <CardContent>
                                    <Typography variant="h4">
                                        {projectHealth?.completedProjects || 0}
                                    </Typography>
                                </CardContent>
                            </Card>
                        </Grid>
                        <Grid item xs={12}>
                            <Card>
                                <CardHeader title="Project Health Details" />
                                <CardContent>
                                    <TableContainer>
                                        <Table>
                                            <TableBody>
                                                {projectHealth?.projects?.map((project) => (
                                                    <TableRow key={project.name}>
                                                        <TableCell>{project.name}</TableCell>
                                                        <TableCell>{project.status}</TableCell>
                                                        <TableCell>{project.progress}%</TableCell>
                                                        <TableCell>
                                                            {project.actualHours} / {project.estimatedHours} hours
                                                        </TableCell>
                                                    </TableRow>
                                                ))}
                                            </TableBody>
                                        </Table>
                                    </TableContainer>
                                </CardContent>
                            </Card>
                        </Grid>
                    </Grid>
                )}

                {activeTab === 1 && (
                    <Grid container spacing={3}>
                        <Grid item xs={12}>
                            <Card>
                                <CardHeader title="Resource Utilization" />
                                <CardContent>
                                    <TableContainer>
                                        <Table>
                                            <TableBody>
                                                {resourceUtilization?.utilization?.map((user, hours) => (
                                                    <TableRow key={user}>
                                                        <TableCell>{user}</TableCell>
                                                        <TableCell>{hours} hours</TableCell>
                                                    </TableRow>
                                                ))}
                                            </TableBody>
                                        </Table>
                                    </TableContainer>
                                </CardContent>
                            </Card>
                        </Grid>
                    </Grid>
                )}

                {activeTab === 2 && (
                    <Grid container spacing={3}>
                        <Grid item xs={12}>
                            <Card>
                                <CardHeader title="Timesheet Summary" />
                                <CardContent>
                                    <Typography>Total Hours: {timesheetSummary?.totalHours || 0}</Typography>
                                    <TableContainer>
                                        <Table>
                                            <TableBody>
                                                {timesheetSummary?.timesheets?.map((timesheet) => (
                                                    <TableRow key={timesheet.id}>
                                                        <TableCell>{timesheet.projectId}</TableCell>
                                                        <TableCell>{timesheet.userId}</TableCell>
                                                        <TableCell>{timesheet.hoursWorked}</TableCell>
                                                    </TableRow>
                                                ))}
                                            </TableBody>
                                        </Table>
                                    </TableContainer>
                                </CardContent>
                            </Card>
                        </Grid>
                    </Grid>
                )}
            </Box>
        </Container>
    );
}

export default Reports;
