import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import { Provider } from 'react-redux';
import store from './store';

// Components
import Login from './components/auth/Login';
import Dashboard from './components/dashboard/Dashboard';
import ProjectManagement from './components/project/ProjectManagement';
import Timesheet from './components/timesheet/Timesheet';
import Reports from './components/reports/Reports';

function App() {
  const theme = createTheme({
    palette: {
      primary: {
        main: '#1976d2',
      },
      secondary: {
        main: '#dc004e',
      },
    },
  });

  return (
    <Provider store={store}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <Router>
          <Routes>
            <Route path="/" element={<Login />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/projects" element={<ProjectManagement />} />
            <Route path="/timesheet" element={<Timesheet />} />
            <Route path="/reports" element={<Reports />} />
          </Routes>
        </Router>
      </ThemeProvider>
    </Provider>
  );
}

export default App;
