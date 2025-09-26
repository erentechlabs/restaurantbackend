# Restaurant Backend System

A backend system for a modern restaurant, designed to manage tables, sessions, menus, and orders. Customers can start a dining session by scanning an NFC tag at their table, browse the menu, and place orders digitally.

Built with **Spring Boot** and provides a RESTful API for all interactions.

---

## Features

- **Session Management**: Start and end customer sessions via unique NFC tag codes.
- **Table Management**: Full CRUD for tables, including NFC tag assignment.
- **Menu Management**: CRUD for menu categories and items.
- **Order Management**: Place and manage orders within active sessions.
- **RESTful API**: Well-defined endpoints for all resources.

---

## Tech Stack

- **Java 24**
- **Spring Boot** (Spring MVC, Spring Data JPA)
- **Jakarta EE** (`jakarta.persistence`)
- **Maven**
- **Lombok**
- **SQL Database** (PostgreSQL, MySQL, or H2)

---

## Prerequisites

- JDK 24+
- Maven 3.6+
- SQL database (PostgreSQL recommended) or H2 for development

---

## Getting Started

1. **Clone the repository:**
    ```bash
    git clone <your-repository-url>
    cd restaurantbackend
    ```

2. **Configure the database:**  
   Edit `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/restaurant_db
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3. **Build the project:**
    ```bash
    mvn clean install
    ```

4. **Run the application:**
    ```bash
    mvn spring-boot:run
    ```
    The app starts on port **8080** by default.

---

## API Endpoints

> Endpoints marked with `(Admin)` require administrative privileges.

### Session API

| Method | Path                                 | Description                                   |
|--------|--------------------------------------|-----------------------------------------------|
| POST   | `/api/sessions/start/nfc/{tagCode}`  | Start a session via NFC tag code              |
| POST   | `/api/sessions/close/code/{code}`    | Close an active session by session code       |

### Order API

| Method | Path                                 | Description                                   |
|--------|--------------------------------------|-----------------------------------------------|
| POST   | `/api/orders`                        | Place a new order for an active session       |
| GET    | `/api/orders/{id}`                   | Retrieve a specific order by ID               |
| GET    | `/api/orders/session/{sessionCode}`  | Get all orders for a session                  |
| PUT    | `/api/orders/{id}`                   | Update an order (Admin)                       |
| DELETE | `/api/orders/{id}`                   | Delete an order (Admin)                       |

### Menu API

| Method | Path                                 | Description                                   |
|--------|--------------------------------------|-----------------------------------------------|
| GET    | `/api/menu/items`                    | Get all menu items                            |
| POST   | `/api/menu/items`                    | Add a new menu item (Admin)                   |
| PUT    | `/api/menu/items/{id}`               | Update a menu item (Admin)                    |
| DELETE | `/api/menu/items/{id}`               | Delete a menu item (Admin)                    |

### Category API

| Method | Path                                 | Description                                   |
|--------|--------------------------------------|-----------------------------------------------|
| GET    | `/api/categories`                    | Get all menu categories                       |
| POST   | `/api/categories`                    | Add a new category (Admin)                    |
| PUT    | `/api/categories/{id}`               | Update a category (Admin)                     |
| DELETE | `/api/categories/{id}`               | Delete a category (Admin)                     |

### Table API

| Method | Path                                 | Description                                   |
|--------|--------------------------------------|-----------------------------------------------|
| GET    | `/api/tables`                        | Get all tables                                |
| GET    | `/api/tables/{id}`                   | Get a table by ID                             |
| POST   | `/api/tables`                        | Add a new table (Admin)                       |
| PUT    | `/api/tables/{id}`                   | Update a table (Admin)                        |
| DELETE | `/api/tables/{id}`                   | Delete a table (Admin)                        |

---