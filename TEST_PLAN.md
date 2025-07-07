# Test Plan: Project & Resource Management System

This document outlines the test strategy and plan for the Project & Resource Management System. It ensures all functional and non-functional requirements (as defined in the SRS and RRD) are validated.

## 1. Objectives
- Verify that all features work as intended (functional requirements)
- Ensure security, performance, and usability (non-functional requirements)
- Identify and resolve defects early in the development cycle

## 2. Test Strategy
- **Unit Testing:** Test individual components (backend models/views, frontend components, including the Login Module)
- **Integration Testing:** Test interactions between modules (API calls, DB integration, authentication workflows)
- **System Testing:** End-to-end testing of the full application, including login/logout flows
- **Acceptance Testing:** Validate against SRS and RRD with user scenarios, including authentication and access control

## 3. Test Types & Tools
| Test Type         | Tool(s)                  | Location           |
|-------------------|--------------------------|--------------------|
| Unit (Frontend)   | Jest, React Testing Lib  | tests/frontend/    |
| Unit (Backend)    | pytest, Django TestCase  | tests/backend/     |
| Unit (Login)      | Jest, Django TestCase    | tests/login/       |
| Integration       | pytest, Postman          | tests/backend/     |
| System (E2E)      | Selenium, Cypress        | tests/             |
| Acceptance        | Manual, checklists       | tests/             |

- **Login Module:** Frontend login form (ReactJS+Bootstrap), backend authentication logic (Django) will be tested for security, usability, and reusability.

## 4. Test Coverage Mapping
- **Login Module:**
  - Test login with valid/invalid credentials (FR-Login-1)
  - Test frontend login form (ReactJS+Bootstrap) usability and accessibility (FR-Login-2, NFR3)
  - Test backend authentication logic (Django) for security, password hashing, and session management (FR-Login-4, NFR1)
  - Test reusability by integrating the module in a sample project (FR-Login-3)
- **User Management:** Test user creation, authentication, role-based access (FR1–FR4, via Login Module)
- **Project Management:** Test CRUD operations on projects (FR5–FR9)
- **Resource Allocation:** Test allocation logic, validation (FR10–FR13)
- **Timesheet Management:** Test timesheet entry, submission (FR14–FR16)
- **Security:** HTTPS, password hashing, secure login/logout (NFR1)
- **Performance:** Page and query response times, login speed (NFR2)
- **Usability:** UI/UX checks on multiple devices, login accessibility (NFR3)

## 5. Test Environments
- **Development:** Local machines (mock/stub external dependencies)
- **Staging:** Mirror production, real integrations
- **Production:** Smoke tests only

## 6. Test Data
- Use anonymized, realistic data for all roles (Super User, PM, Developer, Tester, Lead)
- Seed scripts in `database/seed/`

## 7. Defect Reporting
- Use issue tracker (e.g., GitHub Issues, Jira)
- Include steps to reproduce, environment, severity, screenshots/logs

## 8. Approval & Sign-off
- Tests must pass in staging before release
- Acceptance criteria reviewed by stakeholders
