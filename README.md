# StayEase - Hotel Room Booking System

## Overview
StayEase is a simple restAPI for managing hotels and bookings.


## Features
- User Registration/Login
- Role Based Access Control (RBAC)
- JWT Authentication

## Tech Stack
- Spring Boot
  - Spring Security     
  - Spring Data JPA
  - Junit and Mockito
- MySQL
- Gradle 

## API Endpoints

  - **Public Endpoints**
    - `POST /register` - Register a user
    - `POST /login` - Login
    - `GET /hotel` - View all available hotels
    - `GET /hotel/{id}` - View hotel

  - **User Endpoints**
    - `Post /hotel/{id}/book` - Book a hotel room
    
  - **Admin Endpoints**
    - `Post /hotel` - Create hotel
    - `DELETE /hotel/{id}` - Delete hotel

  - **Hotel Manager Endpoints**
   
    - `PUT /hotel/{id}` - Update Hotel
    - `DELETE /bookings/{bookingId}` - Cancel a booking


## Prerequisites

- Java 21
- Gradle 8+
- MySQL 8+

## Build

### Run with Docker Compose
```sh
 docker compose up --build
```

### Run locally
```sh
 git clone https://github.com/your-repo/stayease.git
 cd stayease
```
update the application.properties file with your local database credentials

```env

spring.datasource.url=jdbc:mysql://localhost:3306/stay_easy?createDatabaseIfNotExist=true
spring.datasource.username={USERNAME}
spring.datasource.password={PASSWORD}
```

```sh
 ./gradlew bootRun 
```
