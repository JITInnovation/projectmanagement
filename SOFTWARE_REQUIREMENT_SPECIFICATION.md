# Software Requirement Specification: Project & Resource Management System

## 1. Introduction

### 1.1. Purpose
This document provides a detailed description of the requirements for the Project & Resource Management System. It is intended for the development team to understand the system's functionality, constraints, and design goals.

### 1.2. Scope
The software will be a web-based application designed to facilitate project creation, resource allocation, and time tracking for a software company. It will manage roles, enforce allocation rules, and provide a platform for users to log their work hours.

### 1.3. Definitions
- **PM:** Project Manager
- **Resource:** A Developer, Tester, or Lead.
- **Allocation:** The percentage of a resource's time dedicated to a specific project.
- **Release Quarter:** A three-month period for a project's release (e.g., Q1, Q2).

## 2. Overall Description

### 2.1. Product Perspective
This is a self-contained system that will manage internal company processes. It will not interface with external systems, to begin with.

### 2.2. User Characteristics
Users are expected to be employees of the software company with varying technical skills. The interface should be intuitive and require minimal training.

### 2.3. Constraints
- The application must be web-based and accessible on modern browsers.
- The system must enforce the 100% allocation rule per resource per quarter.
- User access must be strictly controlled based on roles.

## 3. Functional Requirements

### 3.1. User Module
- **FR1:** The system shall allow a Super User to create a user account with a name, email, and role.
- **FR2:** The system shall require users to log in with their credentials (email and password).
- **FR3:** Authenticated users shall only be able to access features permitted for their role.
- **FR4:** A user, upon first login, should be prompted to complete their profile.

### 3.2. Project Module
- **FR5:** A PM shall be able to create a new project record.
- **FR6:** The project record shall include fields for: Project Name, Domain, Projected Start/End Dates, and Projected Release Quarter.
- **FR7:** A PM shall be able to edit a project to add: Actual Kick-off Date, Actual Start/End Dates, and Actual Release Quarter.
- **FR8:** A PM shall be able to define the number of required resources by role (Developer, Tester, Lead).
- **FR9:** A PM shall be able to input the planned month-wise allocation for different work types.

### 3.3. Resource Allocation Module
- **FR10:** A PM shall be able to view a list of available resources.
- **FR11:** A PM shall be able to assign a resource to a project with a specific allocation percentage.
- **FR12:** The system shall validate any new allocation. If the resource's total allocation in that quarter exceeds 100%, the system shall reject the allocation and notify the PM.
- **FR13:** The system shall display a resource's current allocation percentage to the PM during the assignment process.

### 3.4. Timesheet Module
- **FR14:** A Developer, Tester, or Lead shall be able to view their allocated projects.
- **FR15:** A resource shall be able to fill a timesheet, specifying the project, task, date, and hours worked.
- **FR16:** A resource shall be able to submit their timesheet for a given period (e.g., weekly).

## 4. Non-Functional Requirements

- **NFR1 (Security):** All data transmission between the client and server must be encrypted via HTTPS. Passwords must be hashed and salted.
- **NFR2 (Performance):** Page load times should not exceed 3 seconds. Database queries for resource availability should execute in under 2 seconds.
- **NFR3 (Usability):** The user interface must be clean, intuitive, and responsive, providing a good user experience on both desktop and tablet devices.
- **NFR4 (Reliability):** The system should have an uptime of 99.5%.

## 5. System Architecture (Proposed)

- **Frontend:** A single-page application (SPA) built with a modern JavaScript framework like **React** or **Vue.js**.
- **Backend:** A RESTful API server built with a framework like **Django (Python)** or **Express.js (Node.js)**.
- **Database:** A relational database like **PostgreSQL** or **MySQL** to store data and enforce relationships.

## 6. Conceptual Database Schema

- **`users`**
  - `id` (PK)
  - `name`
  - `email` (unique)
  - `password_hash`
  - `role_id` (FK to roles)

- **`roles`**
  - `id` (PK)
  - `name` (e.g., 'Super User', 'Project Manager', 'Developer')

- **`projects`**
  - `id` (PK)
  - `name`
  - `domain`
  - `projected_start_date`
  - `projected_end_date`
  - `projected_release_quarter`
  - `actual_start_date`
  - `actual_end_date`
  - `actual_release_quarter`
  - `actual_kickoff_date`

- **`project_resource_requirements`**
  - `id` (PK)
  - `project_id` (FK to projects)
  - `role_id` (FK to roles)
  - `count`

- **`project_allocations`**
  - `id` (PK)
  - `project_id` (FK to projects)
  - `user_id` (FK to users)
  - `allocation_percentage`

- **`timesheets`**
  - `id` (PK)
  - `user_id` (FK to users)
  - `project_id` (FK to projects)
  - `task_description`
  - `hours_worked`
  - `date`
