# E-Commerce

## Overview

This is a fully functional E-Commerce REST API built using Spring Boot. 
The application allows users to manage products, organize them into groups, 
manage shopping carts, and process orders efficiently.

## Features

- **Product Management**: Add, update, delete, and view products.

- **Product Grouping** : Organize products into categories for better navigation.

- **Shopping Cart**: Add and remove items from the cart.

- **Order Processing**: Place orders and track their status.

- **User Authentication**: Secure endpoints with user authentication.

## Technologies Used:

- **Backend**: Spring Boot, Spring Data JPA, Hibernate

- **Database**: MySQL

- **Build Tool**: Gradle

- **Security**: Spring Security

- **API Documentation**: Swagger

## Installation & Setup

**Prerequisites**:

- Java 21+

- Gradle

- MySQL (or any preferred sql database)

**Steps to run**:
1. Clone the repository
   ```
   git clone https://github.com/opach16/Ecommerce.git
   ```
2. Configure the database in `application.yaml` file:
   ```
   spring:
   datasource:
    url: 'jdbc:mysql://localhost:3306/<your-db-name>?allowPublicKeyRetrieval=true&useSSL=false'
    username: <your-username>
    password: <your-password>
   ```
3. Build and run the application:
   ```
   ./gradlew build
   ./gradlew bootRun
   ```
4. Access the app:  
   - **API**: http://localhost:8080  
   - **API Documentation**: http://localhost:8080/swagger-ui/index.html

## Contact

For any questions or support, contact:
- **Author**: Konrad
- **Email**: opach16@outlook.com
   
