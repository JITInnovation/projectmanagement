# Product Requirement Document: Project & Resource Management System

## 1. Introduction

This document outlines the requirements for a Project & Resource Management System. The system will serve as a centralized platform for software companies to manage projects, allocate resources, and track time, ensuring efficient resource utilization and project delivery.

The primary goal is to provide project managers with the tools to plan projects, forecast resource needs, and allocate staff effectively, while enabling team members to report their time on assigned tasks. A super user will manage system access and user roles.

## 2. User Personas

| Role              | Description                                                                                                | Responsibilities                                                                                                                                                                                                                                                              |
| ----------------- | ---------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Super User**    | The system administrator.                                                                                  | - Create and manage user accounts for all roles (Project Manager, Developer, Tester, Lead).<br>- Assign roles to new users.                                                                                                                                                           |
| **Project Manager** | Responsible for the successful planning, execution, and closure of projects.                               | - Create new projects and define their attributes (name, domain, dates, etc.).<br>- Define resource requirements for projects (e.g., 2 Developers, 1 Tester).<br>- Allocate specific resources to projects.<br>- Track resource allocation to ensure no individual is over-allocated (>100%).<br>- Monitor project timelines and resource usage. |
| **Developer**     | A software developer responsible for writing code and completing technical tasks.                          | - Log in to the system to view personal details and assigned projects.<br>- Fill and submit timesheets for work done on allocated projects and tasks.                                                                                                                             |
| **Tester**        | A quality assurance engineer responsible for testing the software and reporting bugs.                      | - Log in to the system to view personal details and assigned projects.<br>- Fill and submit timesheets for work done on allocated projects and tasks.                                                                                                                             |
| **Lead**          | A senior team member (e.g., Tech Lead, QA Lead) who guides a team and contributes to the project.        | - Log in to the system to view personal details and assigned projects.<br>- Fill and submit timesheets for work done on allocated projects and tasks.                                                                                                                             |

## 3. Features

### 3.1. User Management (Super User)

- **User Creation:** The Super User can create new user accounts by providing basic information (Name, Email) and assigning a role (Project Manager, Developer, Tester, Lead).
- **User Registration:** Once created, the user receives credentials to log in and complete their profile with any additional required details.

### 3.2. Project Management (Project Manager)

- **Project Creation:** PMs can create a new project and fill in the following details:
  - Project Name
  - Project Domain
  - Projected Start Date & Projected End Date
  - Projected Release Quarter
- **Project Details Management:** PMs can update projects with actual data as it becomes available:
  - Actual Kick-off Date
  - Actual Start Date & Actual End Date
  - Actual Release Quarter
- **Resource Requirements:** PMs can specify the number of resources needed for each role (Developer, Tester, Lead).
- **Monthly Allocation Planning:** PMs can define the month-wise total allocation for each type of work (e.g., Development: 80 hours in Jan, 100 hours in Feb).

### 3.3. Resource Allocation (Project Manager)

- **Resource Assignment:** PMs can assign specific, available resources (Developers, Testers, Leads) to their projects.
- **Allocation Control:** The system will prevent a resource from being allocated more than 100% of their capacity within the same release quarter. For example, if a developer is allocated 60% to Project A, they can only be allocated a maximum of 40% to Project B if both projects are in the same quarter.

### 3.4. Timesheet Management (Developer, Tester, Lead)

- **Timesheet Entry:** Users can fill out a timesheet for each project they are allocated to.
- **Task-Based Logging:** Time can be logged against specific tasks within a project.

## 4. Assumptions

- All users will have access to a modern web browser.
- A standard work week and capacity will be defined for calculating allocation percentages (e.g., 40 hours/week).
- Release quarters are standard calendar quarters (Q1: Jan-Mar, Q2: Apr-Jun, etc.).

## 5. Out of Scope

- Financial management (budgeting, invoicing).
- Client and stakeholder management features.
- Detailed task management features (e.g., Gantt charts, Kanban boards).
- Document management system.
