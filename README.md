# Currency Converter API

A RESTful Currency Converter API built with **Spring Boot 3**, secured using **JWT Authentication**, integrated with the **Frankfurter Exchange Rate API**, and implemented with **enterprise-style logging using Log4j2**.

---

# Features

* JWT Authentication (Bearer Token)
* Login Endpoint
* Get Supported Currencies
* Get Latest Exchange Rates
* Get Historical Exchange Rates
* Get Exchange Rate Between Two Currencies
* Currency Conversion
* Global Exception Handling
* Request Validation
* Enterprise Logging (Log4j2 XML Configuration)
* Request ID & Trace ID Support
* REST Client Integration
* Layered Architecture
* No Database Required (In-Memory User)

---

# Technology Stack

| Technology        | Version |
| ----------------- | ------- |
| Java              | 17      |
| Spring Boot       | 3.5.x   |
| Spring Security   | 6.x     |
| Spring Validation | Latest  |
| Log4j2            | Latest  |
| JWT (JJWT)        | 0.12.x  |
| Lombok            | Latest  |
| Maven             | Latest  |

---

# Project Structure

```
src/main/java
└── com.example.demo
    ├── controller
    │   ├── AuthController
    │   └── CurrencyController
    │
    ├── service
    │   └── CurrencyService
    │
    ├── client
    │   └── FrankfurterClient
    │
    ├── dto
    │   ├── LoginRequest
    │   ├── LoginResponse
    │   ├── ConvertRequest
    │   ├── ConvertResponse
    │   └── ExchangeRateResponse
    │
    ├── security
    │   ├── config
    │   │   ├── SecurityConfig
    │   │   └── JwtAuthenticationEntryPoint
    │   │
    │   ├── filter
    │   │   └── JwtAuthenticationFilter
    │   │
    │   ├── service
    │   │   ├── JwtService
    │   │   ├── AuthService
    │   │   └── UserService
    │   │
    │   ├── model
    │   │   └── UserPrincipal
    │   │
    │   └── constant
    │       └── SecurityConstants
    │
    ├── config
    ├── exception
    ├── logging
    └── DemoApplication
```

---

# Authentication

This API uses **JWT Bearer Token Authentication**.

Only the login endpoint is publicly accessible.

```
POST /api/auth/login
```

All other endpoints require:

```
Authorization: Bearer <JWT_TOKEN>
```

---

# Default User

| Username | Password |
| -------- | -------- |
| admin    | admin123 |

The project currently uses an in-memory user. No database is required.

---

# API Endpoints

## Login

```
POST /api/auth/login
```

Request

```json
{
  "username": "admin",
  "password": "admin123"
}
```

Response

```json
{
  "accessToken": "<jwt-token>",
  "tokenType": "Bearer",
  "expiresIn": 3600
}
```

---

## Get Supported Currencies

```
GET /api/currencies
```

Authorization

```
Bearer Token
```

---

## Get Latest Exchange Rates

```
GET /api/rates?base=USD
```

---

## Get Specific Exchange Rate

```
GET /api/rates/USD/IDR
```

---

## Convert Currency

```
POST /api/convert
```

Request

```json
{
  "amount": 100,
  "from": "USD",
  "to": "IDR"
}
```

---

## Historical Exchange Rate

```
GET /api/history?date=2025-07-01&base=USD
```

---

# Security Flow

```
Client

    │

POST /api/auth/login

    │

AuthenticationManager

    │

UserService

    │

Password Validation

    │

JwtService

    │

Generate JWT

    │

Client receives Token

    │

Authorization: Bearer <token>

    │

JwtAuthenticationFilter

    │

Validate Token

    │

SecurityContext

    │

Controller
```

---

# Logging

The application uses **Log4j2** with XML configuration.

Features:

* Console Logging
* Rolling File Logging
* Trace ID
* Request ID
* Request Logging
* Response Logging
* Error Logging
* Service Logging
* External API Logging

Example

```
2026-07-17 14:50:20.469

traceId=123456

requestId=987654

INFO

CurrencyService

Currency conversion success

USD -> IDR
```

---

# Exception Handling

Handled exceptions include:

* Currency Not Found
* Invalid Request
* JWT Expired
* Invalid JWT
* Authentication Failed
* Validation Error
* Internal Server Error

---

# External API

Exchange rates are retrieved from the Frankfurter API.

Base URL

```
https://api.frankfurter.dev/v1
```

---

# Configuration

application.yml

```yaml
spring:
  application:
    name: currency-converter

frankfurter:
  url: https://api.frankfurter.dev/v1

jwt:
  secret: YOUR_SECRET_KEY
  expiration: 3600000
```

---

# Running the Project

Clone

```
git clone <repository-url>
```

Build

```
mvn clean install
```

Run

```
mvn spring-boot:run
```

Or

```
Run DemoApplication.java
```

---

# Testing

### Login

```
POST /api/auth/login
```

Copy the returned JWT.

Use it on every secured endpoint.

```
Authorization: Bearer <JWT_TOKEN>
```

---

# Future Improvements

* Refresh Token
* User Registration
* Database Authentication (MySQL/PostgreSQL)
* Role Based Access Control (RBAC)
* Docker Support
* Swagger/OpenAPI
* Unit Test & Integration Test
* Redis Token Blacklist
* OAuth2 Login
* Rate Limiting
* Micrometer & Prometheus Metrics

---

# Author

Developed using Spring Boot with a clean layered architecture following enterprise backend development practices.
