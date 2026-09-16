# 🛒 Apni Dukaan — E-Commerce Backend

**Apni Dukaan** is a backend REST API for an e-commerce application built using **Java and Spring Boot**. The project focuses on building a secure, scalable backend with JWT authentication, user management, category and product management, pagination, searching, sorting, and centralized exception handling.

The project is being developed with a layered architecture following common Spring Boot backend development practices.

---

## 🚀 Features

### 🔐 Authentication & Security

* User registration
* User login
* Password encryption using BCrypt
* JWT-based authentication
* Stateless Spring Security configuration
* Protected API endpoints
* Custom `UserDetailsService`
* JWT authentication filter
* Role/authority-ready security architecture

### 👤 User Management

* User registration
* User login
* User profile endpoint
* Email-based user identification
* Account enabled/disabled validation

### 📂 Category Management

* Create category
* Get all categories
* Get category by ID
* Update category
* Delete category
* Duplicate category validation
* Category not-found handling

### 📦 Product Management

* Create product
* Get all products
* Get product by ID
* Update product
* Delete product
* Product-category relationship
* Duplicate product validation
* Product not-found handling

### 🔎 Product Search

Products can be searched by name using case-insensitive partial matching.

Example:

```text
GET /api/v1/product/search?name=iphone
```

Search supports partial matches such as:

```text
iphone
IPHONE
iPhone
```

### 📄 Pagination

Product listing supports pagination.

Example:

```text
GET /api/v1/product?page=0&size=8
```

Pagination information includes:

* Current page
* Page size
* Total elements
* Total pages
* First/last page information

### ↕️ Sorting

Products can be sorted using Spring Data's `Pageable`.

Examples:

```text
GET /api/v1/product?page=0&size=5&sort=price,asc
```

```text
GET /api/v1/product?page=0&size=5&sort=price,desc
```

```text
GET /api/v1/product?page=0&size=5&sort=name,asc
```

```text
GET /api/v1/product?page=0&size=5&sort=createdAt,desc
```

### 🔍 Search + Pagination + Sorting

These features can also be combined.

Example:

```text
GET /api/v1/product/search?name=iphone&page=0&size=5&sort=price,asc
```

---

## 🏗️ Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

### Controller Layer

Handles incoming HTTP requests and returns API responses.

```text
AuthController
CategoryController
ProductController
UserController
```

### Service Layer

Contains business logic and coordinates between controllers and repositories.

```text
UserService
CategoryService
ProductService
```

### Repository Layer

Uses Spring Data JPA to communicate with the database.

```text
UserRepository
CategoryRepository
ProductRepository
```

### Security Layer

Responsible for authentication and JWT processing.

```text
JwtService
JwtAuthenticationFilter
CustomUserDetailsService
CustomUserDetails
SecurityConfig
```

### Exception Layer

Centralizes application-specific exception handling.

```text
CategoryNotFoundException
CategoryAlreadyExistsException
ProductNotFoundException
ProductAlreadyExistsException
GlobalExceptionHandler
```

---

## 🛠️ Tech Stack

| Technology        | Purpose                        |
| ----------------- | ------------------------------ |
| Java 21           | Programming Language           |
| Spring Boot 3.4.x | Backend Framework              |
| Spring Web        | REST APIs                      |
| Spring Data JPA   | Database Access                |
| Spring Security   | Authentication & Authorization |
| JWT / JJWT        | Token-based Authentication     |
| PostgreSQL        | Relational Database            |
| Hibernate         | ORM                            |
| Lombok            | Boilerplate Reduction          |
| Maven             | Dependency Management & Build  |
| IntelliJ IDEA     | Development Environment        |
| Postman           | API Testing                    |

---

## 🔐 JWT Authentication Flow

The application uses JWT for stateless authentication.

```text
User
 ↓
Login
 ↓
AuthController
 ↓
UserService
 ↓
Validate email & password
 ↓
Generate JWT
 ↓
Return token
```

For protected endpoints:

```text
Client
 ↓
Authorization: Bearer <JWT>
 ↓
JwtAuthenticationFilter
 ↓
Extract token
 ↓
Extract email
 ↓
Load UserDetails
 ↓
Validate token
 ↓
Set Authentication in SecurityContext
 ↓
Controller
```

Example header:

```text
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

## 📌 API Endpoints

### Authentication

| Method | Endpoint                | Description                  | Authentication |
| ------ | ----------------------- | ---------------------------- | -------------- |
| POST   | `/api/v1/auth/register` | Register a new user          | Public         |
| POST   | `/api/v1/auth/login`    | Login user and generate JWT  | Public         |
| GET    | `/api/v1/auth/test`     | Test authentication endpoint | Public         |

---

### User

| Method | Endpoint               | Description                      |
| ------ | ---------------------- | -------------------------------- |
| GET    | `/api/v1/user/profile` | Get authenticated user's profile |

Protected using JWT.

---

### Category

| Method | Endpoint                | Description        |
| ------ | ----------------------- | ------------------ |
| POST   | `/api/v1/category`      | Create category    |
| GET    | `/api/v1/category`      | Get all categories |
| GET    | `/api/v1/category/{id}` | Get category by ID |
| PUT    | `/api/v1/category/{id}` | Update category    |
| DELETE | `/api/v1/category/{id}` | Delete category    |

All category management endpoints require authentication.

---

### Product

| Method | Endpoint                 | Description             |
| ------ | ------------------------ | ----------------------- |
| POST   | `/api/v1/product`        | Create product          |
| GET    | `/api/v1/product`        | Get products            |
| GET    | `/api/v1/product/{id}`   | Get product by ID       |
| PUT    | `/api/v1/product/{id}`   | Update product          |
| DELETE | `/api/v1/product/{id}`   | Delete product          |
| GET    | `/api/v1/product/search` | Search products by name |

---

## 📄 Product API Examples

### Create Product

```http
POST /api/v1/product
Authorization: Bearer <JWT>
Content-Type: application/json
```

Request:

```json
{
    "name": "iPhone 17",
    "description": "Latest Apple smartphone",
    "price": 79999.00,
    "stockQuantity": 10,
    "categoryId": 1
}
```

---

### Get Products

```http
GET /api/v1/product?page=0&size=10
Authorization: Bearer <JWT>
```

---

### Search Products

```http
GET /api/v1/product/search?name=iphone
Authorization: Bearer <JWT>
```

---

### Update Product

```http
PUT /api/v1/product/1
Authorization: Bearer <JWT>
Content-Type: application/json
```

Request:

```json
{
    "name": "iPhone 17 Pro",
    "description": "Updated product description",
    "price": 99999.00,
    "stockQuantity": 15,
    "categoryId": 1
}
```

---

### Delete Product

```http
DELETE /api/v1/product/1
Authorization: Bearer <JWT>
```

---

## 🗄️ Database

The project uses **PostgreSQL** with **Spring Data JPA/Hibernate**.

Current core entities include:

```text
User
Category
Product
```

The product and category relationship allows products to be associated with categories.

The project will be extended with order-related entities as development continues.

---

## ⚙️ Configuration

Create/update:

```text
src/main/resources/application.properties
```

Example configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/apni_dukaan
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=YOUR_SECRET_KEY
jwt.expiration=86400000
```

> Do not commit real database passwords or JWT secrets to GitHub. Use environment variables or external configuration for production deployments.

---

## ▶️ Running the Project

### 1. Clone the repository

```bash
git clone https://github.com/Saransh21k/Apni-Dukaan.git
```

### 2. Open the project

Open the project in IntelliJ IDEA or another Java IDE.

### 3. Configure PostgreSQL

Create a PostgreSQL database:

```sql
CREATE DATABASE apni_dukaan;
```

Update the database credentials in `application.properties`.

### 4. Build the project

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn spring-boot:run
```

The application runs by default on:

```text
http://localhost:8080
```

---

## 🧪 Testing

API endpoints can be tested using **Postman**.

Recommended testing flow:

```text
1. Register User
       ↓
2. Login
       ↓
3. Copy JWT
       ↓
4. Add Bearer Token
       ↓
5. Create Category
       ↓
6. Create Product
       ↓
7. Get Products
       ↓
8. Search / Sort / Paginate
       ↓
9. Update Product
       ↓
10. Delete Product
```

---

## 🧩 Exception Handling

The application uses custom exceptions and centralized exception handling.

Examples:

```text
CategoryNotFoundException
ProductNotFoundException
CategoryAlreadyExistsException
ProductAlreadyExistsException
```

Typical HTTP responses:

```text
404 NOT FOUND
```

for resources that don't exist.

```text
409 CONFLICT
```

for duplicate resources.

---

## 📈 Current Development Status

```text
Authentication & JWT        ✅
User Module                 ✅
Category CRUD               ✅
Product CRUD                ✅
Product-Category Relation   ✅
Exception Handling          ✅
Pagination                  ✅
Search                      ✅
Sorting                     ✅
Order Module                🚧
```

---

## 🔮 Planned Features

The project is being developed incrementally. Planned functionality includes:

* Order management
* Order items
* User-order relationships
* Product stock management during checkout
* Order total calculation
* Order status management
* Role-based authorization
* Advanced product filtering
* Product sorting improvements
* API validation
* API documentation with Swagger/OpenAPI
* Unit and integration testing
* Dockerization
* Production-ready configuration

---

## 🎯 Learning Objectives

This project is also being used to develop practical backend engineering skills, including:

* Building REST APIs with Spring Boot
* Designing layered backend architectures
* Implementing JWT authentication
* Working with Spring Security
* Using Spring Data JPA
* Designing entity relationships
* Working with PostgreSQL
* Implementing pagination and sorting
* Creating search APIs
* Designing custom exceptions
* Testing APIs with Postman
* Understanding production-oriented backend patterns

---

## 👨‍💻 Author

**Saransh Mahajan**

Computer Science Engineering | Java Backend Development

GitHub:
https://github.com/Saransh21k

---

## ⭐ Project

If you find this project useful or are interested in the development journey, feel free to explore the repository and follow its progress.
