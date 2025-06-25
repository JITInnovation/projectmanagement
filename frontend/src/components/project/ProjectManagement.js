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
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useSelector } from 'react-redux';

const ProjectManagement = () => {
    const [projects, setProjects] = useState([]);
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();
    const token = useSelector((state) => state.auth.token);

    useEffect(() => {
        fetchProjects();
    }, []);

    const fetchProjects = async () => {
        try {
            const response = await fetch('http://localhost:8090/api/projects', {
                headers: {
                    'Authorization': `Bearer ${token}`,
                },
            });
            if (response.ok) {
                const data = await response.json();
                setProjects(data);
            }
        } catch (error) {
            console.error('Error fetching projects:', error);
        } finally {
            setLoading(false);
        }
    };

    const handleCreateProject = () => {
        navigate('/projects/create');
    };

    const handleViewProject = (projectId) => {
        navigate(`/projects/${projectId}`);
    };

    if (loading) {
        return <div>Loading...</div>;
    }

    return (
        <Container>
            <Box sx={{ my: 4 }}>
                <Typography variant="h4" gutterBottom>
                    Project Management
                </Typography>
                <Button
                    variant="contained"
                    color="primary"
                    onClick={handleCreateProject}
                    sx={{ mb: 3 }}
                >
                    Create New Project
                </Button>
                <Paper>
                    <TableContainer>
                        <Table>
                            <TableHead>
                                <TableRow>
                                    <TableCell>Name</TableCell>
                                    <TableCell>Release</TableCell>
                                    <TableCell>Status</TableCell>
                                    <TableCell>Start Date</TableCell>
                                    <TableCell>End Date</TableCell>
                                    <TableCell>Progress</TableCell>
                                    <TableCell>Actions</TableCell>
                                </TableRow>
                            </TableHead>
                            <TableBody>
                                {projects.map((project) => (
                                    <TableRow key={project.id}>
                                        <TableCell>{project.name}</TableCell>
                                        <TableCell>{project.release}</TableCell>
                                        <TableCell>
                                            <span style={{
                                                color: project.status === 'COMPLETED' ? 'green' :
                                                       project.status === 'ON_HOLD' ? 'orange' : 'blue'
                                            }}>
                                                {project.status}
                                            </span>
                                        </TableCell>
                                        <TableCell>
                                            {new Date(project.startDate).toLocaleDateString()}
                                        </TableCell>
                                        <TableCell>
                                            {new Date(project.endDate).toLocaleDateString()}
                                        </TableCell>
                                        <TableCell>
                                            {project.progress}%
                                        </TableCell>
                                        <TableCell>
                                            <Button
                                                variant="outlined"
                                                size="small"
                                                onClick={() => handleViewProject(project.id)}
                                            >
                                                View
                                            </Button>
                                        </TableCell>
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

export default ProjectManagement;
