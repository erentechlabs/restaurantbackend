
# Restaurant Backend System

This project is a backend system for a modern restaurant, designed to manage tables, sessions, menus, and orders. A key feature is the ability for customers to start a dining session by scanning an NFC tag at their table, enabling them to browse the menu and place orders digitally.

The application is built with Spring Boot and provides a RESTful API for all interactions.

## Features

-   **Session Management**: Start and end customer sessions. Sessions are initiated via a unique NFC tag code assigned to each table.
-   **Table Management**: Full CRUD (Create, Read, Update, Delete) functionality for restaurant tables, including linking them to NFC tags.
-   **Menu Management**: Full CRUD for menu categories (e.g., "Appetizers", "Main Courses") and menu items.
-   **Order Management**: Customers can place orders within an active session. The system supports adding multiple items to an order.
-   **RESTful API**: A well-defined API for interacting with the system's resources.

## Tech Stack

-   **Java 24**: The core programming language.
-   **Spring Boot**: Framework for building the application.
    -   **Spring MVC**: For creating RESTful web endpoints.
    -   **Spring Data JPA**: For data persistence and database interaction.
-   **Jakarta EE**: Uses for JPA entity mappings. `jakarta.persistence`
-   **Maven**: Dependency management and build tool.
-   **Lombok**: To reduce boilerplate code (e.g., getters, setters, constructors).
-   **Database**: The project is configured to work with a SQL database (e.g., PostgreSQL, MySQL, or H2). Configuration is managed in . `application.properties`

## Prerequisites

Before you begin, ensure you have the following installed:

-   JDK 24 or later
-   Maven 3.6 or later
-   A running SQL database instance (like PostgreSQL) or a configured in-memory database like H2.

## Getting Started

Follow these steps to get the project up and running on your local machine.

**1. Clone the repository:**
``` bash
git clone <your-repository-url>  
cd restaurantbackend
``` 
**2. Configure the database:** Open the file and update the `spring.datasource.*` properties to match your database configuration. `src/main/resources/application.properties`

Example for PostgreSQL:
``` 
spring.datasource.url=jdbc:postgresql://localhost:5432/restaurant_db  
spring.datasource.username=your_username  
spring.datasource.password=your_password  
spring.jpa.hibernate.ddl-auto=update
``` 

**3. Build the project:** Use Maven to build the project and install dependencies.
``` 
mvn clean install
``` 

**4. Run the application:** You can run the application using the Spring Boot Maven plugin:
``` 
mvn spring-boot:run
``` 

The application will start on the configured port (default is 8080).

## API Endpoints

The API provides endpoints to manage the restaurant's resources. Endpoints marked with `(Admin)` are typically protected and require administrative privileges.

----------

----------

### Session API

Method

Path

Description

`POST`

`/api/sessions/start/nfc/{tagCode}`

Starts a new session for a table via its NFC tag code.

`POST`

`/api/sessions/close/code/{code}`

Closes an active session using its session code.

----------

----------

### Order API

Method

Path

Description

`POST`

`/api/orders`

Places a new order for an active session.

`GET`

`/api/orders/{id}`

Retrieves a specific order by its ID.

`GET`

`/api/orders/session/{sessionCode}`

Retrieves all orders for a specific session.

`PUT`

`/api/orders/{id}`

Updates an existing order. `(Admin)`

`DELETE`

`/api/orders/{id}`

Deletes an order. `(Admin)`

----------

----------

### Menu API

Method

Path

Description

`GET`

`/api/menu/items`

Retrieves all items on the menu.

`POST`

`/api/menu/items`

Adds a new item to the menu. `(Admin)`

`PUT`

`/api/menu/items/{id}`

Updates an existing menu item. `(Admin)`

`DELETE`

`/api/menu/items/{id}`

Deletes a menu item. `(Admin)`

----------

----------

### Category API

Method

Path

Description

`GET`

`/api/categories`

Retrieves all menu categories.

`POST`

`/api/categories`

Adds a new menu subCategory. `(Admin)`

`PUT`

`/api/categories/{id}`

Updates an existing subCategory. `(Admin)`

`DELETE`

`/api/categories/{id}`

Deletes a subCategory. `(Admin)`

----------

----------

### Table API

Method

Path

Description

`GET`

`/api/tables`

Retrieves all tables in the restaurant.

`GET`

`/api/tables/{id}`

Retrieves a specific table by its ID.

`POST`

`/api/tables`

Adds a new table. `(Admin)`

`PUT`

`/api/tables/{id}`

Updates an existing table. `(Admin)`

`DELETE`

`/api/tables/{id}`

Deletes a table. `(Admin)`

----------
