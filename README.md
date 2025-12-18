# 📰 News Aggregator Backend API

A backend REST API built using Spring Boot that allows users to register, authenticate using JWT, manage their news preferences, and fetch personalized news using the GNews API.

---

## 🚀 Features

### Authentication & Authorization
- User Registration
- User Login
- JWT-based authentication
- Secure APIs using Spring Security

### User Preferences
- Save user news preferences:
  - Country
  - Category
  - Language
- Fetch logged-in user's preferences
- Update preferences multiple times (upsert supported)

### News Aggregation
- Fetch personalized news based on saved preferences
- Integration with GNews API
- Clean and simplified news response

---

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT (jjwt)
- Spring Data JPA
- H2 In-Memory Database
- RestTemplate
- Maven

---

## 📂 Project Structure

com.example.news_aggregator
 ├── auth
 ├── security
 ├── user
 ├── preference
 ├── news
 │   ├── client
 │   ├── dto
 │   ├── controller
 │   └── service
 └── NewsAggregatorApplication.java

---

## 🔐 API Endpoints

### Authentication

POST /api/register

Request Body:
{
  "username": "harish",
  "email": "harish@example.com",
  "password": "pass123"
}

POST /api/login

Request Body:
{
  "username": "harish",
  "password": "pass123"
}

Response:
JWT Token

---

### Preferences (JWT Required)

GET /api/preferences

POST /api/preferences

Request Body:
{
  "country": "us",
  "category": "technology",
  "language": "en"
}

---

### News (JWT Required)

GET /api/news

Sample Response:
{
  "articles": [
    {
      "title": "Google swaps Pixel Launcher search",
      "description": "All to push AI mode",
      "url": "https://example.com",
      "image": "https://example.com/image.jpg",
      "publishedAt": "2025-12-17T04:06:00Z",
      "sourceName": "Android Police"
    }
  ]
}

---

## ⚙️ Configuration

application.properties

spring.application.name=news-aggregator

gnews.api.key=MY_API_KEY
gnews.api.url=https://gnews.io/api/v4/top-headlines

spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true

---

## 🧪 Database

Uses H2 in-memory database.

H2 Console:
http://localhost:8080/h2-console


