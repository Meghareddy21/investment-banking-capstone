📌 Project Title
Investment Banking Deal Pipeline Management Portal
📖 Project Overview

Investment banks manage multiple deals simultaneously across various stages such as prospecting, evaluation, negotiation, and closure.
This project simulates a Deal Pipeline Management Portal that allows banks to track, manage, and secure deal information using modern full-stack technologies.

The application supports role-based access control, ensuring sensitive information like deal value is visible only to authorized users.

🎯 Objectives

Implement JWT-based authentication and authorization

Enable secure role-based access (ADMIN / USER)

Track investment deals across multiple pipeline stages

Provide a modern full-stack architecture using React and Spring Boot

Persist data using MongoDB

Support containerized deployment using Docker

🏗️ System Architecture

Frontend: React (SPA)

Backend: Spring Boot (REST APIs)

Database: MongoDB

Security: JWT Authentication

Deployment: Docker & Docker Compose

👥 User Roles & Permissions
USER

Login

View deals

Create deals

Update deal stage

Add notes

❌ Cannot view or edit deal value

❌ Cannot delete deals

ADMIN

All USER permissions

View & update deal value (sensitive)

Delete deals

Manage users (create, activate/deactivate)

🔐 Authentication & Authorization

JWT tokens are generated on successful login

Tokens are stored in browser localStorage

Axios interceptor attaches JWT to secured API requests

Role-based access is enforced at backend and frontend levels

📊 Deal Pipeline Stages

Deals progress through the following lifecycle stages:

Prospect

UnderEvaluation

TermSheetSubmitted

Closed

Lost

The pipeline view groups deals based on their current stage, providing clear workflow visibility.

📂 Backend API Endpoints
Authentication

POST /api/auth/login – Login and receive JWT

Deal Management

POST /api/deals – Create deal

GET /api/deals – List all deals

GET /api/deals/{id} – Get deal details

PUT /api/deals/{id} – Update deal (non-sensitive fields)

PATCH /api/deals/{id}/stage – Update deal stage

PATCH /api/deals/{id}/value – Update deal value (ADMIN only)

POST /api/deals/{id}/notes – Add notes

DELETE /api/deals/{id} – Delete deal (ADMIN only)

🖥️ Frontend Features

Login page with JWT authentication

Deal Pipeline dashboard (Kanban-style view)

Create Deal form (ADMIN only)

Role-based UI rendering

Secure API communication using Axios

React Router v6 for navigation

🧪 Testing
Backend

JUnit 5

Mockito

Service-layer unit tests

Frontend

Jest

React Testing Library

Core component testing

🐳 Docker Deployment

The application supports containerized deployment using Docker:

React frontend served via Nginx

Spring Boot backend runs on port 8080

MongoDB runs as a separate container

Docker Compose orchestrates all services.

🚀 How to Run the Application
Backend

Run using Spring Tool Suite:

Run As → Spring Boot App

Frontend
npm install
npm start

Docker (Optional)
docker-compose up

🧠 Conclusion

This project demonstrates a secure, scalable, and role-based Deal Pipeline Management system using modern full-stack technologies.
It closely simulates real-world investment banking workflows and satisfies all Virtusa capstone requirements."# investment-banking-capstone" 
