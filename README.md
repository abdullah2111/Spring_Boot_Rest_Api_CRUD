
# Spring CRUD API with JWT Authentication

This is a simple and secure Spring Boot application implementing a CRUD API with JWT authentication. The application demonstrates both public and protected API endpoints:

- **Public API**: This API is open for all and does not require any authentication. For example, accessing the teacher data.

- **Protected API**: The student data and CRUD operations require JWT authentication to ensure secure access. You must sign up, log in, and receive a token to access the student data.

## Features

- Public API for fetching teacher data.

- CRUD Operations for managing student data (Create, Read, Update, Delete).

- JWT Authentication to secure student-related API endpoints.

- Spring Boot for creating and managing the application.

- PostgreSQL as the database for storing teacher and student information.

## Table of Contents

Technologies Used

Setup Instructions

API Endpoints

How to Use

Contributing

License

## Technologies Used

- **Spring Boot:** The framework used to build the API.

- **JWT (JSON Web Tokens):** For securing the endpoints.

- **PostgreSQL:** The database to store teacher and student data.

- **Spring Security:** To handle the authentication and authorization of users.

- **Maven:** For dependency management.

## Setup Instructions
### Prerequisites

- JDK 17 or above

- PostgreSQL database running locally or on a remote server

- Maven for dependency management

### 1. Clone the Repository
``` git clone https://github.com/your-username/spring-crud-api-with-jwt.git ```


### 2. Configure Database
Ensure you have PostgreSQL set up and running.

- Create a database in PostgreSQL, e.g., `student_db`.
- Update the `application.properties` or `application.yml` file with your PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/student_db
spring.datasource.username=your-db-username
spring.datasource.password=your-db-password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 3. Build the Project

- Using Maven, build the project:

```mvn clean install```

## 4. Run the Application

### Run the Spring Boot application:

```mvn spring-boot:run```


### This will start the application at `http://localhost:8080`.

## API Endpoints

### Public Endpoints (No Authentication Required)

#### 1. Get all Teachers
- **URL**: `/public/teachers`
- **Method**: `GET`
- **Response**: List of all teachers stored in the database.

**Example Response**:
```json
[
  {
    "id": 1,
    "name": "Md. Rakib Hasan",
    "department": "Mathematics",
    "email": "rakib.hasan@schoolbd.com"
  },
  
]
```

### Protected Endpoints (JWT Authentication Required)

#### 1. Sign Up (Register User)
- **URL**: `/auth/signup`
- **Method**: `POST`
- **Body**: JSON containing user details for registration.

**Example Request Body**:
```json
{
  "username": "student1",
  "password": "password123"
}
```
#### 2. Log In (Get JWT Token)
- **URL**: `/auth/login`
- **Method**: `POST`
- **Body**: JSON with username and password.

**Example Request Body**:
```json
{
  "username": "student1",
  "password": "password123"
}
```


- **Response**: Returns a JWT token for authenticating requests to protected endpoints.

**Example Response**:
```json
{
  "token": "your-jwt-token-here"
}
```

#### 3. Get All Students (Requires JWT Authentication)
- **URL:** `/students`
- **Method:** `GET`
- **Headers**:
    - `Authorization: Bearer {JWT_TOKEN}`
- **Response:** List of all students (only accessible with a valid JWT token).

---

## How to Use

### 1. Register and Log In
- Use the **Sign Up** endpoint to create a new user.
- Log in using the **Log In** endpoint to receive a JWT token.
- Use the token in the `Authorization` header for all protected student-related requests.

### 2. Fetch Teacher Data (Public)
Simply visit the `/public/teachers` endpoint to get teacher data. No token or authentication required.

### 3. CRUD Operations on Students (Authenticated)
Use the JWT token to perform CRUD operations on the students. All student-related endpoints are protected and require the token in the `Authorization` header:

```bash
Authorization: Bearer {JWT_TOKEN}
```


