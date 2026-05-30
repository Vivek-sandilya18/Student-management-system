# 🎓 Student Management System

A Student Management System built using Java, Spring Boot, and MySQL. The application provides student registration, login, OTP verification, live search functionality, and complete student record management through RESTful APIs.

## ✨ Features

* Student Registration
* Student Login
* OTP Verification
* View Student Details
* Get Student By Name
* Live Search Students By Name
* Update Student Information
* Delete Student Records
* RESTful APIs
* MySQL Database Integration

## 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Lombok

## 📂 Project Structure

```text
src/main/java
│
├── controller
├── service
├── repository
├── entity
├── config
└── StudentManagementSystemApplication
```

## 🔐 Authentication APIs

### Register Student

POST /auth/register

### Login Student

POST /auth/login

### Verify OTP

POST /auth/verify-otp

## 👨‍🎓 Student Management APIs

### Get All Students

GET /students

### Search Student By Name

GET /students/search?name={studentName}

### Add Student

POST /students

### Update Student

PUT /students/{id}

### Delete Student

DELETE /students/{id}

## 🧠 Concepts Implemented

* Layered Architecture
* REST API Development
* CRUD Operations
* Live Search Functionality
* OTP Verification Workflow
* Database Integration using JPA/Hibernate
* Clean Code Structure

## ⚡ Getting Started

### Clone Repository

```bash
git clone https://github.com/your-username/student-management-system.git
```

### Configure Database

Update application.properties

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_db
spring.datasource.username=root
spring.datasource.password=your_password
```

### Run Project

```bash
mvn spring-boot:run
```

## 🎯 Learning Outcomes

* Spring Boot Application Development
* REST API Design & Development
* Database Management using MySQL
* OTP Verification Implementation
* Backend Development Fundamentals
* JPA/Hibernate Integration
* Layered Architecture Design

## 🔮 Future Enhancements

* Spring Security Integration
* JWT Authentication
* Swagger API Documentation
* Role-Based Access Control
* Docker Deployment
* Cloud Hosting

## 👨‍💻 Author

**Vivek Sharma**

Aspiring Software Engineer | Java Developer | Spring Boot | MySQL | MongoDB

## ⭐ Support

If you found this project useful, consider giving it a ⭐ on GitHub.
