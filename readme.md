# Restaurant Backend System

This backend system manages restaurant tables, sessions, menu categories, subcategories, menu items, and orders. Customers can start a session by scanning an NFC tag at their table, browse the menu (with categories, subcategories, and items), and place orders digitally.

Built with **Spring Boot** and provides a RESTful API for all interactions.

---

## Features

- **Session Management**: Start and end customer sessions via unique NFC tag codes.
- **Table Management**: Full CRUD for tables, including NFC tag assignment.
- **Menu Management**: Nested categories, subcategories, and menu items. Supports complex menu structures.
- **Order Management**: Place and manage orders within active sessions.
- **RESTful API**: Well-defined endpoints for all resources.

---

## Tech Stack

- **Java 21+**
- **Spring Boot** (Spring MVC, Spring Data JPA)
- **Jakarta EE** (`jakarta.persistence`)
- **Maven**
- **Lombok**
- **SQL Database** (PostgreSQL, MySQL, or H2)

---

## Prerequisites

- JDK 21+
- Maven 3.6+
- SQL database (PostgreSQL recommended) or H2 for development

---

## Getting Started

1. **Clone the repository:**
        ```bash
        git clone https://github.com/erentechlabs/restaurantbackend
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


## REST API Endpoint Reference

### Table Endpoints (`/api/v1/tables`)
- `GET /` — Get all tables. Returns a list of all tables in the restaurant.
- `POST /` — Add a new table. Request body: `TableDTO`.
- `PUT /update/{id}` — Update a table by ID. Request body: `TableDTO`.
- `DELETE /delete/{id}` — Delete a table by ID.

### Category Endpoints (`/api/v1/categories`)
- `POST /` — Create a new category (with nested subcategories and menu items). Request body: `CategoryDTO` (see example below).
- `GET /` — Get all categories. Returns a list of all categories.
- `PUT /{id}` — Update a category (with nested subcategories and menu items). Request body: `CategoryDTO`.
- `DELETE /{id}` — Delete a category by ID.

### SubCategory Endpoints (`/api/v1/categories/{categoryId}/subcategories`)
- `GET /` — Get all subcategories for a category. Returns a list of subcategories for the given category.
- `GET /{id}` — Get a subcategory by ID.
- `POST /` — Create a new subcategory. Request body: `SubCategoryDTO`.
- `PUT /{id}` — Update a subcategory by ID. Request body: `SubCategoryDTO`.
- `DELETE /{id}` — Delete a subcategory by ID.

### Menu Endpoints (`/api/v1/menu`)
- `GET /` — Get all menu items. Returns a list of all menu items.
- `GET /{id}` — Get a menu item by ID.
- `POST /` — Create a new menu item. Request body: `MenuItemDTO`.
- `PUT /{id}` — Update a menu item by ID. Request body: `MenuItemDTO`.
- `DELETE /{id}` — Delete a menu item by ID.

### Order Endpoints (`/api/v1/orders/table`)
- `POST /{sessionCode}` — Create a new order for a session. Request body: `List<OrderItemDTO>`.
- `PUT /{sessionCode}` — Update an order for a session. Request body: `List<OrderItemDTO>`.
- `PUT /{orderId}/item/{oldItemName}` — Update an item in an order. Request body: `OrderItemDTO`.

### Session Endpoints (`/api/v1/sessions`)
- `GET /` — Get all active sessions. Returns a list of active sessions.
- `POST /start/{nfctagCode}` — Start a session by NFC tag code. Returns the created session.
- `POST /close/{nfctagCode}` — Close a session by NFC tag code.
- `POST /start-and-order/{nfctagCode}` — Start a session and place an order at the same time. Request body: `List<OrderItemDTO>`. Returns the created order.

---
