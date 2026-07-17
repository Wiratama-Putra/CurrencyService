# 💱 Currency Converter API

REST API sederhana yang dibangun menggunakan **Spring Boot** untuk melakukan konversi mata uang secara real-time menggunakan **Frankfurter API**.

Project ini dibuat sebagai latihan membangun aplikasi backend dengan standar enterprise, meliputi:

- REST API
- Layered Architecture
- DTO Pattern
- Exception Handling
- Validation
- External API Integration
- Logging menggunakan Log4j2
- Standard Response
- Clean Code

---

# Technology Stack

| Technology | Version |
|------------|----------|
| Java | 17 |
| Spring Boot | 3.x |
| Maven | 3.9+ |
| Spring Web | Latest |
| Spring Validation | Latest |
| Log4j2 | Latest |
| Lombok | Latest |
| Frankfurter API | https://frankfurter.dev |

---

# Features

## Currency

- Get all supported currencies
- Get latest exchange rates
- Get specific exchange rate
- Convert currency
- Get historical exchange rates

---

# Architecture

```
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Frankfurter Client
   │
   ▼
Frankfurter API
```

---

# Project Structure

```
src
├── controller
│      CurrencyController
│
├── service
│      CurrencyService
│
├── client
│      FrankfurterClient
│
├── dto
│      ConvertRequest
│      ConvertResponse
│      ExchangeRateResponse
│
├── config
│      RestClientConfig
│      LogConfig
│
├── exception
│      CurrencyNotFoundException
│      GlobalExceptionHandler
│
├── logging
│      LoggingMarker
│      LoggingUtil
│      LoggingAspect
│
└── CurrencyApplication
```

---

# API Endpoint

## 1. Get Supported Currency

### Request

```
GET /api/currencies
```

### Response

```json
{
    "USD": "United States Dollar",
    "EUR": "Euro",
    "IDR": "Indonesian Rupiah"
}
```

---

## 2. Get Latest Rate

### Request

```
GET /api/rates?base=USD
```

### Response

```json
{
    "base": "USD",
    "date": "2026-07-17",
    "rates": {
        "IDR": 16352.17,
        "JPY": 149.32
    }
}
```

---

## 3. Get Specific Rate

### Request

```
GET /api/rates/USD/IDR
```

### Response

```json
{
    "amount":1,
    "from":"USD",
    "to":"IDR",
    "rate":16352.17,
    "result":16352.17
}
```

---

## 4. Convert Currency

### Request

```
POST /api/convert
```

Body

```json
{
    "amount":100,
    "from":"USD",
    "to":"IDR"
}
```

### Response

```json
{
    "amount":100,
    "from":"USD",
    "to":"IDR",
    "rate":16352.17,
    "result":1635217
}
```

---

## 5. Historical Rate

### Request

```
GET /api/history?date=2025-12-01&base=USD
```

### Response

```json
{
    "base":"USD",
    "date":"2025-12-01",
    "rates":{
        "IDR":16180.75
    }
}
```

---

# Validation

Convert Request

| Field | Validation |
|---------|------------|
| amount | Required |
| from | Required |
| to | Required |

Example Error

```json
{
    "timestamp":"2026-07-17T09:00:00",
    "status":400,
    "message":"Amount must be greater than zero"
}
```

---

# Exception Handling

Global Exception Handler

Supported Exception

- CurrencyNotFoundException
- MethodArgumentNotValidException
- Exception

Example

```json
{
    "timestamp":"2026-07-17T09:00:00",
    "status":404,
    "message":"Exchange rate not found"
}
```

---

# Logging

Logging menggunakan Log4j2.

Kategori log:

- REQUEST
- RESPONSE
- BUSINESS
- PERFORMANCE
- ERROR

Contoh log:

```
REQUEST
POST /api/convert

BUSINESS
Convert USD to IDR

PERFORMANCE
Execution Time : 120 ms

RESPONSE
Status : SUCCESS

ERROR
Currency not found
```

---

# External API

Project menggunakan Frankfurter API.

Documentation

https://frankfurter.dev/docs

Example

```
GET https://api.frankfurter.dev/latest?base=USD
```

---

# Run Project

Clone repository

```
git clone https://github.com/your-username/currency-converter.git
```

Masuk ke project

```
cd currency-converter
```

Build

```
mvn clean install
```

Run

```
mvn spring-boot:run
```

Application

```
http://localhost:8080
```

---

# Testing

## Get Currency

```
curl http://localhost:8080/api/currencies
```

---

## Latest Rate

```
curl "http://localhost:8080/api/rates?base=USD"
```

---

## Specific Rate

```
curl http://localhost:8080/api/rates/USD/IDR
```

---

## Convert

```
curl --location 'http://localhost:8080/api/convert' \
--header 'Content-Type: application/json' \
--data '{
    "amount":100,
    "from":"USD",
    "to":"IDR"
}'
```

---

## Historical

```
curl "http://localhost:8080/api/history?date=2025-01-01&base=USD"
```

---

# Future Improvements

- Swagger / OpenAPI
- Unit Test
- Integration Test
- Docker
- Redis Cache
- Database (PostgreSQL)
- Exchange Rate History
- Favorite Currency
- Currency Calculator
- Scheduled Exchange Rate Sync
- JWT Authentication
- API Rate Limiter
- Micrometer Monitoring
- Prometheus
- Grafana Dashboard

---

# Design Principles

- SOLID Principles
- Clean Code
- Layered Architecture
- DTO Pattern
- Exception Handling
- Constructor Injection
- Validation
- Centralized Logging
- Enterprise Coding Standard

---

# Author

Developed using Java & Spring Boot as a backend practice project following enterprise application architecture and best practices.
