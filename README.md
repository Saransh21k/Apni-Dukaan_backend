# 🛒 Apni Dukaan — E-Commerce Backend

A **Java Spring Boot REST API** for an e-commerce application, built using a layered architecture with **JWT-based authentication, product and category management, search, pagination, sorting, and centralized exception handling**.

The project is being developed as a backend-focused application to strengthen practical **Java, Spring Boot, REST API, JPA/Hibernate, SQL, and backend architecture** skills.

---

## 🚀 Features

* 🔐 JWT-based authentication
* 👤 User authentication and profile API
* 📦 Product CRUD operations
* 🏷️ Category CRUD operations
* 🔗 Product–Category relationship
* 🔎 Product search by name
* 📄 Pagination
* ↕️ Sorting
* ⚠️ Custom exception handling
* 🗄️ Database persistence using JPA/Hibernate
* 🧱 Layered backend architecture

---

## 🏗️ Architecture

The application follows a clean layered architecture:

```text
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
Database
```

### Main Layers

**Controller**

* Handles HTTP requests and responses
* Defines REST endpoints
* Validates request parameters

**Service**

* Contains business logic
* Processes requests before interacting with repositories

**Repository**

* Handles database operations using Spring Data JPA

**Entity**

* Represents database tables and relationships

**DTO**

* Separates API request/response objects from database entities

---

## 🛠️ Tech Stack

| Technology      | Usage                          |
| --------------- | ------------------------------ |
| Java            | Backend development            |
| Spring Boot     | REST API development           |
| Spring Security | Authentication & authorization |
| JWT             | Stateless authentication       |
| Spring Data JPA | Database access                |
| Hibernate       | ORM                            |
| PostgreSQL      | Relational database            |
| Maven           | Dependency management          |
| Git & GitHub    | Version control                |
| IntelliJ IDEA   | Development                    |

---

# 🔐 Authentication

Apni Dukaan uses **JWT-based stateless authentication**.

### Authentication Flow

```text
User
 │
 │ Register / Login
 ▼
Authentication API
 │
 ▼
JWT Token
 │
 ▼
Client sends token
 │
 │ Authorization: Bearer <token>
 ▼
JWT Authentication Filter
 │
 ▼
Spring Security
 │
 ▼
Protected API
```

Protected endpoints require a valid JWT token.

---

# 📦 Product APIs

### Create Product

```http
POST /api/v1/product
```

### Get All Products

```http
GET /api/v1/product
```

Supports pagination and sorting.

Example:

```http
GET /api/v1/product?page=0&size=5&sort=price,asc
```

### Get Product by ID

```http
GET /api/v1/product/{id}
```

### Update Product

```http
PUT /api/v1/product/{id}
```

### Delete Product

```http
DELETE /api/v1/product/{id}
```

---

## 🔎 Product Search

Products can be searched by name using case-insensitive partial matching.

```http
GET /api/v1/product/search?name=iphone
```

Search also supports pagination and sorting:

```http
GET /api/v1/product/search?name=iphone&page=0&size=5&sort=price,asc
```

---

# 📄 Pagination & Sorting

The product listing API uses Spring's `Pageable`.

Example:

```http
GET /api/v1/product?page=0&size=5
```

Sorting:

```http
GET /api/v1/product?page=0&size=5&sort=price,asc
```

Descending:

```http
GET /api/v1/product?page=0&size=5&sort=price,desc
```

Multiple sorting examples:

```text
sort=name,asc
sort=price,desc
sort=createdAt,desc
```

---

# 🏷️ Category APIs

### Create Category

```http
POST /api/v1/category
```

### Get All Categories

```http
GET /api/v1/category
```

### Get Category by ID

```http
GET /api/v1/category/{id}
```

### Update Category

```http
PUT /api/v1/category/{id}
```

### Delete Category

```http
DELETE /api/v1/category/{id}
```

---

# ⚠️ Exception Handling

The application uses custom exceptions for common backend scenarios such as:

* Product not found
* Category not found
* Duplicate category
* Invalid resource requests

This keeps API error handling cleaner and separates business errors from controller logic.

---

# 🗄️ Database

The application uses **PostgreSQL** as the relational database.

Main entities currently include:

```text
User
 │
 └── Authentication / Profile

Category
 │
 └── Products

Product
 │
 └── Category
```

---

# ▶️ Running the Project

### 1. Clone the repository

```bash
git clone <your-repository-url>
```

### 2. Open the project

Open the project in **IntelliJ IDEA** or another Java IDE.

### 3. Configure PostgreSQL

Create a PostgreSQL database and configure the database properties in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/apni_dukaan
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
```

Do not commit real database credentials to GitHub.

### 4. Build the project

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn spring-boot:run
```

The API will then be available locally through the configured Spring Boot port.

---

# 🧪 API Testing

The APIs can be tested using tools such as:

* Postman
* IntelliJ HTTP Client
* cURL

Typical testing flow:

```text
Register User
      ↓
Login
      ↓
Receive JWT
      ↓
Send JWT with Authorization header
      ↓
Access Protected APIs
      ↓
Create Category
      ↓
Create Product
      ↓
Search / Sort / Paginate Products
```

---

# 📚 What I Learned

This project is helping me build practical experience with:

* Java backend development
* Spring Boot REST APIs
* Spring Security
* JWT authentication
* JPA/Hibernate
* Entity relationships
* DTO-based API design
* Service and repository patterns
* PostgreSQL
* Pagination with `Page` and `Pageable`
* Dynamic sorting
* Database querying
* Custom exception handling
* Git and GitHub workflow

---

# 🚧 Roadmap

Current development:

```text
Authentication & JWT       ✅
User Module                ✅
Category CRUD              ✅
Product CRUD               ✅
Product-Category Relation  ✅
Exception Handling         ✅
Pagination                 ✅
Search                     ✅
Sorting                    ✅
Order Module               🚧
```

### Planned Order Module

```text
User
 │
 └── Order
       │
       └── OrderItem
              │
              └── Product
```

Planned functionality includes:

* Order creation
* Order items
* Order status management
* Order history
* Order total calculation
* Product purchase-price snapshot

---

# 🎯 Project Goal

The goal of **Apni Dukaan** is to build a realistic backend application while developing strong fundamentals in **Java and Spring Boot backend engineering**.

The project is intentionally being developed feature-by-feature rather than relying on generated code, with focus on understanding the architecture and implementation behind each feature.

---

## 👨‍💻 Author

**Saransh Mahajan**

Computer Science Engineer
Java | Spring Boot | REST APIs | SQL | Backend Development

---

⭐ If you find the project interesting, feel free to explore the repository and follow its development.
