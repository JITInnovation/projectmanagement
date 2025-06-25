# Project Management System

A comprehensive project management and resource allocation system built with ReactJS and Spring Boot.

## Project Structure

```
project-management/
├── backend/                 # Spring Boot backend
│   ├── src/main/java/      # Java source code
│   ├── src/main/resources/ # Configuration files
│   └── pom.xml            # Maven configuration
└── frontend/               # React frontend
    ├── src/               # React source code
    └── package.json      # NPM dependencies
```

## Features

1. Role-based access control
   - System Administrator (SA)
   - Project Managers
   - Developers/Testers (Resources)
   - Product Owners

2. Project Management
   - Create and manage projects
   - Define project timelines and resource requirements
   - Assign releases to projects

3. Resource Management
   - Daily timesheet entry
   - Project resource allocation
   - Burndown tracking

4. Reporting
   - Project health metrics
   - Resource utilization reports
   - Time tracking reports

## Setup Instructions

### Backend Setup

1. Install Java 17 and Maven
2. Navigate to backend directory
3. Run `mvn clean install`
4. Start the application with `mvn spring-boot:run`

### Frontend Setup

1. Install Node.js and npm
2. Navigate to frontend directory
3. Run `npm install`
4. Start the development server with `npm start`

### Database Setup

1. Install MongoDB
2. Ensure MongoDB is running on default port (27017)
3. The application will automatically create necessary collections

## API Endpoints

### Authentication
- POST `/api/auth/login`
- POST `/api/auth/register`

### Projects
- GET `/api/projects`
- POST `/api/projects`
- PUT `/api/projects/{id}`
- DELETE `/api/projects/{id}`

### Timesheet
- GET `/api/timesheet`
- POST `/api/timesheet`
- PUT `/api/timesheet/{id}`

### Users
- GET `/api/users`
- POST `/api/users`
- PUT `/api/users/{id}`
