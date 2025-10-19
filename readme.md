A robust RESTful API backend application for restaurant management built with Spring Boot, featuring menu management, table management, order processing, and session tracking.

## 📋 Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [Docker Support](#docker-support)
- [API Documentation](#api-documentation)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)

## ✨ Features

- **Menu Management**
    - Restaurant management
    - Category and subcategory organization
    - Menu item CRUD operations

- **Table Management**
    - Table creation and management
    - Table status tracking

- **Session Management**
    - Customer session tracking
    - Table session association

- **Order Management**
    - Order creation and processing
    - Order tracking

- **Security**
    - Spring Security integration
    - RESTful API protection

- **API Documentation**
    - Interactive Swagger UI
    - OpenAPI 3.0 specification

## 🛠 Technologies

- **Java 17**
- **Spring Boot 3.5.6**
- **Spring Data JPA** - Database persistence
- **Spring Security** - Authentication and authorization
- **PostgreSQL** - Primary database
- **Lombok** - Reduce boilerplate code
- **Maven** - Dependency management
- **Swagger/OpenAPI** - API documentation
- **Docker** - Containerization

## 📦 Prerequisites

Before running this application, make sure you have the following installed:

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+
- Docker (optional, for containerized deployment)

## 🚀 Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd restaurantbackend
   ```

2. **Create PostgreSQL Database**
   ```sql
   CREATE DATABASE restaurantbackend;
   ```

3. **Configure Database Credentials**

   Update the database credentials in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/restaurantbackend
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

## ⚙️ Configuration

The application can be configured through `application.properties`:

```properties
# Server Configuration
server.port=8080
server.address=0.0.0.0

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/restaurantbackend
spring.datasource.username=postgres
spring.datasource.password=123456

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=create
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# File Upload Configuration
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

> **Note**: For production, change `spring.jpa.hibernate.ddl-auto` to `update` or `validate`

## 🏃 Running the Application

### Using Maven

```bash
# Development mode
./mvnw spring-boot:run

# Or build and run the JAR
./mvnw clean package
java -jar target/restaurantbackend-0.0.1-SNAPSHOT.jar
```

### Using Maven Wrapper (Windows)

```bash
mvnw.cmd spring-boot:run
```

The application will start on `http://localhost:8080`

## 🐳 Docker Support

### Build Docker Image

```bash
# First, build the application
./mvnw clean package

# Build Docker image
docker build -t restaurantbackend:latest .
```

### Run with Docker

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=${POSTGRESQL_URL} \
  -e SPRING_DATASOURCE_USERNAME=${POSTGRESQL_USER} \
  -e SPRING_DATASOURCE_PASSWORD=${POSTGRESQL_PASSWORD} \
  restaurantbackend:latest
```

### Docker Compose (Recommended)

Create a `docker-compose.yml`:

```yaml
version: '3.8'
services:
  postgres:
    image: postgres:15-alpine
    environment:
      POSTGRES_DB: restaurantbackend
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: 123456
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/restaurantbackend
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: 123456
    depends_on:
      - postgres

volumes:
  postgres_data:
```

Run with:
```bash
docker-compose up
```

## 📚 API Documentation

Once the application is running, access the interactive API documentation:

- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/v3/api-docs

## 📁 Project Structure

```
restaurantbackend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/restaurantbackend/restaurantbackend/
│   │   │       ├── config/              # Configuration classes
│   │   │       │   ├── SecurityConfig.java
│   │   │       │   ├── WebConfig.java
│   │   │       │   └── GlobalExceptionHandler.java
│   │   │       ├── controller/          # REST Controllers
│   │   │       │   ├── menu/
│   │   │       │   ├── order/
│   │   │       │   ├── session/
│   │   │       │   └── table/
│   │   │       ├── dto/                 # Data Transfer Objects
│   │   │       ├── entity/              # JPA Entities
│   │   │       │   ├── menu/
│   │   │       │   ├── order/
│   │   │       │   └── table/
│   │   │       ├── repository/          # Spring Data JPA Repositories
│   │   │       ├── service/             # Business Logic Services
│   │   │       └── RestaurantbackendApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                            # Test files
├── target/                              # Build output
├── Dockerfile
├── pom.xml                              # Maven configuration
└── README.md
```

## 🔌 API Endpoints

### Menu Management

- **Restaurants**
    - `GET /api/restaurants` - Get all restaurants
    - `GET /api/restaurants/{id}` - Get restaurant by ID
    - `POST /api/restaurants` - Create restaurant
    - `PUT /api/restaurants/{id}` - Update restaurant
    - `DELETE /api/restaurants/{id}` - Delete restaurant

- **Categories**
    - `GET /api/categories` - Get all categories
    - `GET /api/categories/{id}` - Get category by ID
    - `POST /api/categories` - Create category
    - `PUT /api/categories/{id}` - Update category
    - `DELETE /api/categories/{id}` - Delete category

- **Subcategories**
    - `GET /api/subcategories` - Get all subcategories
    - `GET /api/subcategories/{id}` - Get subcategory by ID
    - `POST /api/subcategories` - Create subcategory
    - `PUT /api/subcategories/{id}` - Update subcategory
    - `DELETE /api/subcategories/{id}` - Delete subcategory

- **Menu Items**
    - `GET /api/menu-items` - Get all menu items
    - `GET /api/menu-items/{id}` - Get menu item by ID
    - `POST /api/menu-items` - Create menu item
    - `PUT /api/menu-items/{id}` - Update menu item
    - `DELETE /api/menu-items/{id}` - Delete menu item

### Table Management

- `GET /api/tables` - Get all tables
- `GET /api/tables/{id}` - Get table by ID
- `POST /api/tables` - Create table
- `PUT /api/tables/{id}` - Update table
- `DELETE /api/tables/{id}` - Delete table

### Session Management

- `POST /api/sessions/start` - Start new session
- `GET /api/sessions/{id}` - Get session details

### Order Management

- `GET /api/orders` - Get all orders
- `GET /api/orders/{id}` - Get order by ID
- `POST /api/orders` - Create order
- `PUT /api/orders/{id}` - Update order

> **Note**: Visit Swagger UI for complete endpoint details, request/response schemas, and interactive testing.

## 🧪 Testing

Run tests using Maven:

```bash
./mvnw test
```

## 🔒 Security

The application uses Spring Security for authentication and authorization. Configure security settings in `SecurityConfig.java`.
