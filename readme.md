# Restaurant Backend System

This project is a backend application for a restaurant management system, developed using Spring Boot. The system manages tables, sessions, menu categories, subcategories, menu items, and orders. Customers can start a session by scanning an NFC tag at their table, browse the menu, and place orders digitally.

---

## Technologies

- **Java 17**
- **Spring Boot 3.5.6**
  - Spring MVC
  - Spring Data JPA
  - Spring Security
- **Maven**
- **PostgreSQL** (Primary database)
- **H2 Database** (For testing environment)
- **Lombok**

---

## Features

- **Table Management**: Full CRUD (Create, Read, Update, Delete) operations for restaurant tables and NFC tag assignment.
- **Session Management**: Start and end customer sessions via unique NFC tag codes.
- **Menu Management**: Supports complex menu structures with nested categories, subcategories, and menu items.
- **Order Management**: Place and manage orders within active sessions.
- **RESTful API**: Well-defined endpoints for all resources.

---

## Getting Started

### Prerequisites

- JDK 17+
- Maven 3.6+
- PostgreSQL database

### Installation

1. **Clone the project:**

   ```bash
   git clone https://github.com/erentechlabs/restaurantbackend.git
   cd restaurantbackend
   ```

2. **Configure the database:**

   Edit the `src/main/resources/application.properties` file:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/restaurant_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Build the project:**

   ```bash
   ./mvnw clean install
   ```

4. **Run the application:**

   ```bash
   ./mvnw spring-boot:run
   ```

   The application will start on port **8080** by default.

---

## REST API Endpoint Reference

All endpoints are prefixed with `/api/v1`.

### Table Endpoints (`/tables`)

- `GET /` — Get all tables.
- `POST /` — Add a new table. Request body: `TableDTO`.
- `PUT /update/{id}` — Update a table by ID. Request body: `TableDTO`.
- `DELETE /delete/{id}` — Delete a table by ID.

### Category Endpoints (`/categories`)

- `GET /` — Get all categories.
- `POST /` — Create a new category. Request body: `CategoryDTO`.
- `PUT /{id}` — Update a category by ID. Request body: `CategoryDTO`.
- `DELETE /{id}` — Delete a category by ID.

### SubCategory Endpoints (`/categories/{categoryId}/subcategories`)

- `GET /` — Get all subcategories for a category.
- `GET /{id}` — Get a subcategory by ID within the specified category.
- `POST /` — Create a new subcategory. Request body: `SubCategoryDTO`.
- `PUT /{id}` — Update a subcategory by ID. Request body: `SubCategoryDTO`.
- `DELETE /{id}` — Delete a subcategory by ID.

### Menu Item Endpoints (`/categories/{categoryId}/subcategories/{subCategoryId}/menu`)

- `GET /` — Get all menu items for a subcategory.
- `GET /{id}` — Get a menu item by ID within the specified subcategory.
- `POST /` — Add a new menu item. Request body: `MenuItemDTO`.
- `PUT /{id}` — Update a menu item by ID. Request body: `MenuItemDTO`.
- `DELETE /{id}` — Delete a menu item by ID.

### Order Endpoints (`/orders/table`)

- `POST /{sessionCode}` — Create a new order for a session. Request body: `List<OrderItemDTO>`.
- `PUT /{sessionCode}` — Add new items to an existing order in a session. Request body: `List<OrderItemDTO>`.
- `PUT /{orderId}/item/{oldItemName}` — Update a specific item in an order. Request body: `OrderItemDTO`.

### Session Endpoints (`/sessions`)

- `GET /` — Get all active sessions.
- `POST /start/{nfctagCode}` — Start a session with an NFC tag code.
- `POST /close/{sessionCode}` — Close a session with a session code.
- `POST /start-and-order/{nfctagCode}` — Start a session and create an order simultaneously. Request body: `List<OrderItemDTO>`.
