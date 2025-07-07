# Project Structure Documentation

This document describes the directory and file organization for the Project & Resource Management System. It provides a reference for developers, testers, and stakeholders to understand the layout and purpose of each major component in the codebase.

## 1. Overview

The system is organized to support a modern web application with a ReactJS frontend, Django backend, and MySQL database, as outlined in the Technology Stack document.

## 2. Directory Structure

```
projectmanagement/
├── frontend/                # ReactJS frontend application
│   ├── public/              # Static assets (index.html, images, etc.)
│   └── src/                 # React source code (components, pages, utils)
│       ├── components/      # Reusable UI components
│       ├── pages/           # Page-level components/routes
│       ├── services/        # API service calls
│       └── ...
├── backend/                 # Django backend application
│   ├── manage.py            # Django management script
│   ├── project/             # Django project settings
│   └── app/                 # Main Django app (models, views, serializers)
├── database/                # Database scripts and migrations
│   ├── migrations/          # Django migration files
│   └── seed/                # Seed data or initial SQL scripts
├── tests/                   # Automated tests (unit, integration, system)
│   ├── frontend/            # Frontend tests (Jest, React Testing Library)
│   └── backend/             # Backend tests (pytest, Django TestCase)
├── docs/                    # Documentation (RRD, SRS, Tech Stack, etc.)
│   ├── PRODUCT_REQUIREMENT_DOCUMENT.md
│   ├── SOFTWARE_REQUIREMENT_SPECIFICATION.md
│   ├── TECHNOLOGY_STACK.md
│   └── ...
├── requirements.txt         # Python dependencies
├── package.json             # Node.js dependencies (frontend)
└── README.md                # Project overview and setup instructions
```

## 3. Mapping to System Modules

- **User Management:** Backend/app, Frontend/src/components/User
- **Project Management:** Backend/app, Frontend/src/components/Project
- **Resource Allocation:** Backend/app, Frontend/src/components/Resource
- **Timesheet Management:** Backend/app, Frontend/src/components/Timesheet
- **Tests:** tests/frontend, tests/backend
- **Documentation:** docs/

## 4. Notes
- Follow the SRS for detailed requirements mapping.
- Refer to the Technology Stack for environment setup and tooling.
