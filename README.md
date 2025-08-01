# Student Management System (Spring Boot)

This project is a Student Management REST API built with:

- Spring Boot 3
- Spring Security
- Spring Data JPA
- PostgreSQL

## 🚀 Features

✅ Register new students  
✅ Secure endpoints with Basic Auth  
✅ Role-based authorization (Admin only access)  
✅ CRUD operations for students  
✅ Password encryption with BCrypt  
✅ Validation with Hibernate Validator

## ⚙️ Technologies Used

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL

## 🔑 Endpoints

| Method | Endpoint             | Description                |
|--------|-----------------------|----------------------------|
| POST   | /api/register        | Register new student       |
| GET    | /api/students        | Get all students (Admin)   |
| GET    | /api/student/{id}    | Get student by ID (Admin)  |
| PUT    | /api/student/{id}    | Update student (Admin)     |
| PATCH  | /api/student/{id}    | Partial update (Admin)     |
| DELETE | /api/student/{id}    | Delete student (Admin)     |


🗂 Database
Default database: PostgreSQL
DDL auto: update


✨ Future Enhancements
JWT authentication


👨‍💻 Author
Muhammadali Qoxxoraliyev
