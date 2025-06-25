import React, { useState, useEffect } from 'react';
import {
    Box,
    Button,
    Container,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Typography,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    TextField,
    Select,
    MenuItem,
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

const Timesheet = () => {
    const [timesheets, setTimesheets] = useState([]);
    const [open, setOpen] = useState(false);
    const [selectedDate, setSelectedDate] = useState(new Date().toISOString().split('T')[0]);
    const [selectedProject, setSelectedProject] = useState('');
    const [hoursWorked, setHoursWorked] = useState(0);
    const [taskDescription, setTaskDescription] = useState('');
    const navigate = useNavigate();
    const token = useSelector((state) => state.auth.token);

    useEffect(() => {
        fetchTimesheets();
    }, []);

    const fetchTimesheets = async () => {
        try {
            const response = await fetch('http://localhost:8090/api/timesheet', {
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
            });
            if (response.ok) {
                const data = await response.json();
                setTimesheets(data);
            }
        } catch (error) {
            console.error('Error fetching timesheets:', error);
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:8090/api/timesheet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify({
                    projectId: selectedProject,
                    date: selectedDate,
                    hoursWorked,
                    taskDescription,
                    status: 'PENDING'
                }),
            });
            if (response.ok) {
                setOpen(false);
                setHoursWorked(0);
                setTaskDescription('');
                fetchTimesheets();
            }
        } catch (error) {
            console.error('Error submitting timesheet:', error);
        }
    };

    const handleViewProjectTimesheets = () => {
        navigate('/timesheet/project');
    };

    return (
        <Container>
            <Box sx={{ my: 4 }}>
                <Typography variant="h4" gutterBottom>
                    Daily Timesheet
                </Typography>
                <Button
                    variant="contained"
                    color="primary"
                    onClick={() => setOpen(true)}
                    sx={{ mb: 3 }}
                >
                    Add Timesheet Entry
                </Button>
                
                <Dialog open={open} onClose={() => setOpen(false)}>
                    <DialogTitle>Add Timesheet Entry</DialogTitle>
                    <DialogContent>
                        <TextField
                            fullWidth
                            label="Date"
                            type="date"
                            value={selectedDate}
                            onChange={(e) => setSelectedDate(e.target.value)}
                            sx={{ mb: 2 }}
                        />
                        <TextField
                            fullWidth
                            label="Project ID"
                            value={selectedProject}
                            onChange={(e) => setSelectedProject(e.target.value)}
                            sx={{ mb: 2 }}
                        />
                        <TextField
                            fullWidth
                            label="Hours Worked"
                            type="number"
                            value={hoursWorked}
                            onChange={(e) => setHoursWorked(e.target.value)}
                            sx={{ mb: 2 }}
                        />
                        <TextField
                            fullWidth
                            multiline
                            rows={4}
                            label="Task Description"
                            value={taskDescription}
                            onChange={(e) => setTaskDescription(e.target.value)}
                        />
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={() => setOpen(false)}>Cancel</Button>
                        <Button onClick={handleSubmit}>Submit</Button>
                    </DialogActions>
                </Dialog>

                <Paper>
                    <TableContainer>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell>Date</TableCell>
                                    <TableCell>Project</TableCell>
                                    <TableCell>Hours Worked</TableCell>
                                    <TableCell>Task Description</TableCell>
                                    <TableCell>Status</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {timesheets.map((timesheet) => (
                                    <TableRow key={timesheet.id}>
                                        <TableCell>
                                            {new Date(timesheet.date).toLocaleDateString()}
                                        </TableCell>
                                        <TableCell>{timesheet.projectId}</TableCell>
                                        <TableCell>{timesheet.hoursWorked}</TableCell>
                                        <TableCell>{timesheet.taskDescription}</TableCell>
                                        <TableCell>{timesheet.status}</TableCell>
                                    </TableRow>
                                ))}
                            </TableBody>
                        </Table>
                    </TableContainer>
                </Paper>
            </Box>
        </Container>
    );
};

export default Timesheet;
