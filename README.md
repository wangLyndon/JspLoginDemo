# User Login and Registration System

## Introduction

This project demonstrates a simple user login and registration system using JSP and Servlets, with MySQL as the database. The project includes:

- **index.jsp**: Entry point with links to login and register pages.
- **login.jsp**: Login form where users can enter their username and password.
- **register.jsp**: Registration form where new users can create an account.
- **home.jsp**: Home page displayed upon successful login.


# Database Table Structure

The system uses a single table called `users` to store user information.

## Users Table

### Table Name: `userinfo`

| Column Name | Data Type   | Constraints              | Description               |
|-------------|-------------|--------------------------|---------------------------|
| userId      | INT         | PRIMARY KEY, AUTO_INCREMENT | Unique identifier for each user |
| userName    | VARCHAR(50) | NOT NULL, UNIQUE         | Username chosen by the user  |
| userPwd     | VARCHAR(50) | NOT NULL                 | User's password             |
| userAge     | INT         | NOT NULL                 | User's age                  |

### SQL Statements

#### Create Database

```sql
CREATE DATABASE jsplogindemo;
USE jsplogindemo;

CREATE TABLE users (
    userId INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(50) NOT NULL UNIQUE,
    userPwd VARCHAR(50) NOT NULL,
    userAge INT NOT NULL
);

INSERT INTO users (userName, userPwd, userAge) VALUES ('testuser', 'testpassword', 25);

