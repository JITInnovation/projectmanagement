import React from 'react';
import { Box, Typography, Grid } from '@mui/material';

const Dashboard = () => {
  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" gutterBottom>
        Dashboard
      </Typography>
      <Grid container spacing={3}>
        <Grid item xs={12}>
          <Typography variant="body1">
            Welcome to the Project Management Dashboard
          </Typography>
        </Grid>
      </Grid>
    </Box>
  );
};

export default Dashboard;
