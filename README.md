# Blog API with Spring Boot

A REST API for a simple blog platform, allowing users to manage posts and comments. This project includes features like database relationships (One-to-Many), data validation, and role-based security.

## Technologies Used

* Java 21
* Spring Boot 3.x
* Spring Security
* Spring Data JPA
* MySQL
* Maven
* Springdoc OpenAPI (Swagger)

## Features

* Full CRUD operations for Posts and Comments.
* Role-based access control (ADMIN, USER).
* Input validation for creating new resources.
* Centralized exception handling.
* Automatic API documentation via Swagger UI.

## How to Run

1.  Clone the repository.
2.  Make sure you have a local MySQL server running.
3.  Create a database named `blog_api`.
4.  Update the database username and password in `src/main/resources/application.properties`.
5.  Run the application.

## API Documentation

Once the application is running, the interactive API documentation (Swagger UI) is available at:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)