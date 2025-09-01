# Synectix ERP Backend

## 📋 Table of Contents

- [Project Description](#project-description)
- [Technology Stack](#technology-stack)
- [Architecture Overview](#architecture-overview)
- [Local Development Setup](#local-development-setup)
- [Database Setup](#database-setup)
- [Project Structure](#project-structure)
- [Module Implementation Progress](#module-implementation-progress)
- [API Documentation](#api-documentation)
- [Authentication & Security](#authentication--security)
- [Testing](#testing)
- [Deployment](#deployment)
- [Contributing](#contributing)
- [Troubleshooting](#troubleshooting)

## 🏢 Project Description

Synectix is a comprehensive Enterprise Resource Planning (ERP) system designed specifically for hobbyist businesses and small-to-medium enterprises. The backend is built using modern Java technologies and provides a robust foundation for inventory management, warehouse operations, user management, and business analytics.

### Key Features

- **Multi-tenant Architecture**: Support for multiple companies with isolated data
- **Comprehensive Inventory Management**: Product catalog, stock tracking, lot/serial number management
- **Warehouse Management**: Multi-warehouse support with location tracking
- **User & Role Management**: Granular permission system with role-based access control
- **Real-time Inventory Tracking**: Live stock updates and transaction recording
- **Audit Trail**: Complete transaction history and user activity logging
- **RESTful API**: Well-documented APIs for frontend integration and third-party systems
- **JWT Authentication**: Secure token-based authentication with multi-factor support

### Business Objectives

- Streamline inventory operations for small businesses
- Provide real-time visibility into stock levels and movements
- Enable efficient warehouse management across multiple locations
- Offer comprehensive reporting and analytics capabilities
- Support business growth with scalable architecture

## 🛠 Technology Stack

### Core Technologies

- **Java 17+**: Latest LTS version with modern language features
- **Spring Boot 3.x**: Enterprise-grade framework for rapid development
- **Spring Security 6.x**: Comprehensive security framework with OAuth2/JWT support
- **Spring Data JPA**: Data access layer with Hibernate ORM
- **PostgreSQL**: Primary database for data persistence
- **Maven/Gradle**: Build automation and dependency management

### Additional Dependencies

- **MapStruct**: Type-safe mapping between DTOs and entities
- **Lombok**: Reduce boilerplate code with annotations
- **Jackson**: JSON serialization/deserialization
- **Hibernate Validator**: Bean validation framework
- **Springdoc OpenAPI**: API documentation generation
- **JUnit 5**: Testing framework with modern testing capabilities
- **Testcontainers**: Integration testing with containerized databases
- **Flyway**: Database migration management

### Development Tools

- **Docker**: Containerization for consistent development environments
- **Docker Compose**: Multi-container application orchestration
- **SonarQube**: Code quality analysis and technical debt management
- **Actuator**: Production-ready monitoring and management endpoints

## 🏗 Architecture Overview

### Layered Architecture

```
┌─────────────────────────────────────────┐
│              Controller Layer            │
│     (REST APIs, Request Handling)       │
├─────────────────────────────────────────┤
│               Service Layer              │
│      (Business Logic, Transactions)     │
├─────────────────────────────────────────┤
│              Repository Layer            │
│       (Data Access, JPA Repositories)   │
├─────────────────────────────────────────┤
│               Entity Layer               │
│        (Domain Models, JPA Entities)    │
└─────────────────────────────────────────┘
```

### Key Design Patterns

- **Repository Pattern**: Clean separation of data access logic
- **DTO Pattern**: Data transfer objects for API communication
- **Builder Pattern**: Fluent object construction with Lombok
- **Dependency Injection**: IoC container managed dependencies
- **Factory Pattern**: Object creation for complex business entities

### Database Design

- **Multi-tenant Strategy**: Company-based data isolation
- **UUID Primary Keys**: Distributed system friendly identifiers
- **Audit Columns**: Created/updated timestamps and user tracking
- **Referential Integrity**: Foreign key constraints for data consistency
- **Indexed Columns**: Optimized queries for frequently accessed data

## 🚀 Local Development Setup

### Prerequisites

Ensure you have the following installed on your development machine:

```bash
# Java Development Kit 17 or higher
java -version
# Should output: openjdk version "17.0.x" or higher

# Git for version control
git --version

# Docker and Docker Compose
docker --version
docker-compose --version

# PostgreSQL (optional, can use Docker)
psql --version
```

### Step 1: Clone the Repository

```bash
git clone https://github.com/aquinofroilan/synectix.git
cd synectix
```

### Step 2: Environment Configuration

Create environment-specific configuration files:

**application-dev.properties**

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/synectix_dev
spring.datasource.username=synectix_user
spring.datasource.password=synectix_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

# JWT Configuration
app.jwt.secret=your-256-bit-secret-key-here
app.jwt.expiration=86400000
app.jwt.refresh-expiration=604800000

# Logging Configuration
logging.level.com.froilan.synectix=DEBUG
logging.level.org.springframework.security=DEBUG

# Actuator Configuration
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=always
```

### Step 3: Database Setup with Docker

Use the provided Docker Compose file for quick database setup:

```bash
# Start PostgreSQL container
docker-compose up -d postgres

# Verify database is running
docker-compose ps
```

**docker-compose.yml**

```yaml
version: '3.8'
services:
  postgres:
    image: postgres:15-alpine
    container_name: synectix-postgres
    environment:
      POSTGRES_DB: synectix_dev
      POSTGRES_USER: synectix_user
      POSTGRES_PASSWORD: synectix_password
    ports:
      - '5432:5432'
    volumes:
      - postgres_data:/var/lib/postgresql/data
      - ./setup.sql:/docker-entrypoint-initdb.d/setup.sql
    networks:
      - synectix-network

volumes:
  postgres_data:

networks:
  synectix-network:
    driver: bridge
```

### Step 4: Build and Run the Application

```bash
# Using Gradle
./gradlew bootRun --args='--spring.profiles.active=dev'

# Or using Maven
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Alternative: Build JAR and run
./gradlew bootJar
java -jar build/libs/synectix-*.jar --spring.profiles.active=dev
```

### Step 5: Verify Installation

```bash
# Check application health
curl http://localhost:8080/actuator/health

# Expected response:
# {"status":"UP","components":{"db":{"status":"UP"},...}}

# Test API endpoint
curl http://localhost:8080/api/health
```

## 🗄 Database Setup

### Manual PostgreSQL Setup

If you prefer not to use Docker:

```sql
-- Create database and user
CREATE DATABASE synectix_dev;
CREATE USER synectix_user WITH PASSWORD 'synectix_password';
GRANT ALL PRIVILEGES ON DATABASE synectix_dev TO synectix_user;

-- Connect to the database and run setup script
\c synectix_dev;
\i setup.sql;
```

### Database Migration

The application uses Flyway for database migrations:

```bash
# Run migrations manually
./gradlew flywayMigrate

# Check migration status
./gradlew flywayInfo

# Repair migration checksums (if needed)
./gradlew flywayRepair
```

### Database Schema Overview

```sql
-- Core Tables
- users                 -- User accounts and authentication
- company               -- Company/organization data
- user_company_role     -- User-company-role associations
- country               -- Country lookup data
- organization_type     -- Organization type lookup
- role                  -- System roles and permissions

-- Inventory Management
- product_category      -- Product categorization
- product              -- Product master data
- warehouse            -- Warehouse locations
- inventory_item       -- Stock levels by location
- inventory_transaction -- All inventory movements

-- Audit and Security
- user_mfa_tokens      -- Multi-factor authentication
- user_warehouse_association -- User-warehouse access
```

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/froilan/synectix/
│   │       ├── config/                 # Configuration classes
│   │       │   ├── security/           # Security configuration
│   │       │   │   ├── jwt/            # JWT token handling
│   │       │   │   └── oauth2/         # OAuth2 configuration
│   │       │   ├── database/           # Database configuration
│   │       │   └── web/                # Web MVC configuration
│   │       ├── controller/             # REST controllers
│   │       │   ├── auth/               # Authentication endpoints
│   │       │   ├── company/            # Company management
│   │       │   │   └── inventory/      # Inventory operations
│   │       │   └── user/               # User management
│   │       ├── service/                # Business logic layer
│   │       │   ├── auth/               # Authentication services
│   │       │   ├── inventory/          # Inventory management
│   │       │   ├── user/               # User management
│   │       │   └── warehouse/          # Warehouse operations
│   │       ├── repository/             # Data access layer
│   │       │   ├── inventory/          # Inventory repositories
│   │       │   ├── user/               # User repositories
│   │       │   └── lookup/             # Lookup data repositories
│   │       ├── model/                  # Domain entities
│   │       │   ├── inventory/          # Inventory entities
│   │       │   ├── lookup/             # Lookup entities
│   │       │   ├── enums/              # Enumeration types
│   │       │   └── dto/                # Data transfer objects
│   │       │       ├── request/        # Request DTOs
│   │       │       └── response/       # Response DTOs
│   │       ├── modules/                # Feature modules
│   │       │   ├── inventory/          # Inventory module
│   │       │   │   ├── mapper/         # MapStruct mappers
│   │       │   │   ├── validator/      # Custom validators
│   │       │   │   └── exception/      # Module exceptions
│   │       │   └── auth/               # Authentication module
│   │       ├── exception/              # Global exception handling
│   │       ├── util/                   # Utility classes
│   │       └── SynectixApplication.java # Main application class
│   └── resources/
│       ├── application.properties      # Main configuration
│       ├── application-dev.properties  # Development config
│       ├── application-prod.properties # Production config
│       ├── db/migration/               # Flyway migration scripts
│       ├── static/                     # Static resources
│       └── templates/                  # Template files
└── test/
    ├── java/
    │   └── com/froilan/synectix/
    │       ├── integration/            # Integration tests
    │       ├── controller/             # Controller tests
    │       ├── service/                # Service tests
    │       ├── repository/             # Repository tests
    │       └── util/                   # Test utilities
    └── resources/
        ├── application-test.properties # Test configuration
        └── test-data/                  # Test data files
```

## 🔄 Module Implementation Progress

### ✅ Completed Modules

#### Authentication & Security Module

- [x] JWT token generation and validation
- [x] User registration and login
- [x] Password encryption with BCrypt
- [x] Role-based access control (RBAC)
- [x] Multi-factor authentication (MFA) support
- [x] Session management
- [x] Password reset functionality

#### Company Management Module

- [x] Company registration and profile management
- [x] Organization type classification
- [x] Country/region support
- [x] Company-user associations
- [x] Multi-tenant data isolation

#### User Management Module

- [x] User profile management
- [x] User-company-role associations
- [x] User activation/deactivation
- [x] User warehouse assignments
- [x] Audit trail for user actions

### 🚧 In Progress Modules

#### Inventory Management Module (75% Complete)

- [x] Product master data management
- [x] Product categorization
- [x] SKU generation and validation
- [x] Product specifications (dimensions, weight, etc.)
- [x] Barcode/QR code support
- [x] Product lifecycle management
- [ ] Product bundling and kits
- [ ] Product variants and attributes
- [ ] Price management and history
- [ ] Product images and attachments

#### Warehouse Management Module (60% Complete)

- [x] Warehouse creation and management
- [x] Warehouse type classification
- [x] Address and location management
- [x] Capacity management
- [x] User-warehouse associations
- [ ] Location hierarchy (zones, aisles, bins)
- [ ] Location capacity and constraints
- [ ] Warehouse layout management
- [ ] Pick path optimization

#### Inventory Tracking Module (40% Complete)

- [x] Inventory item tracking
- [x] Stock level management
- [x] Basic transaction recording
- [x] Lot number tracking
- [x] Serial number tracking
- [ ] Expiration date management
- [ ] Cycle counting
- [ ] Physical inventory counts
- [ ] Stock movement optimization
- [ ] Inventory valuation methods

### 📋 Planned Modules

#### Transaction Management Module

- [ ] Inventory receipts
- [ ] Inventory issues/shipments
- [ ] Stock transfers between warehouses
- [ ] Inventory adjustments
- [ ] Transaction approval workflows
- [ ] Batch transaction processing
- [ ] Transaction reversal/cancellation

#### Reporting & Analytics Module

- [ ] Real-time inventory dashboards
- [ ] Stock level reports
- [ ] Transaction history reports
- [ ] Inventory valuation reports
- [ ] Movement analysis
- [ ] Slow-moving inventory reports
- [ ] ABC analysis
- [ ] Demand forecasting

#### Purchase Order Management Module

- [ ] Supplier management
- [ ] Purchase order creation
- [ ] PO approval workflows
- [ ] Receiving against POs
- [ ] Vendor performance tracking
- [ ] Cost analysis
- [ ] Automated reordering

#### Quality Control Module

- [ ] Inspection procedures
- [ ] Quality checkpoints
- [ ] Non-conformance tracking
- [ ] Certificate of analysis
- [ ] Quality metrics
- [ ] Supplier quality ratings

#### Integration Module

- [ ] REST API for external systems
- [ ] Webhook support
- [ ] File import/export utilities
- [ ] EDI integration
- [ ] Accounting system integration
- [ ] E-commerce platform sync

### 🔄 Continuous Improvements

- [ ] Performance optimization
- [ ] Security enhancements
- [ ] Mobile application support
- [ ] Advanced search capabilities
- [ ] Notification system
- [ ] Workflow engine
- [ ] Document management
- [ ] Business intelligence

## 📚 API Documentation

### OpenAPI/Swagger Documentation

Access the interactive API documentation at:

```
http://localhost:8080/swagger-ui.html
```

### Key API Endpoints

#### Authentication Endpoints

```http
POST /api/auth/register          # User registration
POST /api/auth/login             # User login
POST /api/auth/refresh           # Token refresh
POST /api/auth/logout            # User logout
POST /api/auth/forgot-password   # Password reset request
POST /api/auth/reset-password    # Password reset confirmation
```

#### Company Management

```http
GET    /api/companies            # List companies
POST   /api/companies            # Create company
GET    /api/companies/{id}       # Get company details
PUT    /api/companies/{id}       # Update company
DELETE /api/companies/{id}       # Delete company
```

#### Inventory Management

```http
GET    /api/company/inventory/products      # List products
POST   /api/company/inventory/products      # Create product
GET    /api/company/inventory/products/{id} # Get product details
PUT    /api/company/inventory/products/{id} # Update product
DELETE /api/company/inventory/products/{id} # Delete product

GET    /api/company/inventory/categories    # List categories
POST   /api/company/inventory/categories    # Create category
```

#### Warehouse Management

```http
GET    /api/company/inventory/warehouses      # List warehouses
POST   /api/company/inventory/warehouses      # Create warehouse
GET    /api/company/inventory/warehouses/{id} # Get warehouse details
PUT    /api/company/inventory/warehouses/{id} # Update warehouse
DELETE /api/company/inventory/warehouses/{id} # Delete warehouse
```

### API Response Format

```json
{
	"success": true,
	"message": "Operation completed successfully",
	"data": {
		// Response data
	},
	"timestamp": "2025-09-01T10:30:00Z",
	"path": "/api/endpoint"
}
```

### Error Response Format

```json
{
	"success": false,
	"error": {
		"code": "VALIDATION_ERROR",
		"message": "Validation failed for one or more fields",
		"details": [
			{
				"field": "productName",
				"message": "Product name is required"
			}
		]
	},
	"timestamp": "2025-09-01T10:30:00Z",
	"path": "/api/endpoint"
}
```

## 🔐 Authentication & Security

### JWT Implementation

- **Token Type**: Bearer tokens with HS256 signing
- **Token Expiration**: 24 hours (configurable)
- **Refresh Token**: 7 days (configurable)
- **Claims**: User ID, username, company ID, roles, permissions

### Security Features

- **Password Hashing**: BCrypt with strength 12
- **CORS Configuration**: Configurable allowed origins
- **SQL Injection Prevention**: Parameterized queries with JPA
- **XSS Protection**: Input sanitization and validation
- **CSRF Protection**: Stateless JWT tokens
- **Rate Limiting**: Configurable per endpoint

### Role-Based Access Control

```java
// Example role hierarchy
ADMIN > MANAGER > USER > READ_ONLY

// Permission examples
- USER_CREATE, USER_READ, USER_UPDATE, USER_DELETE
- PRODUCT_CREATE, PRODUCT_READ, PRODUCT_UPDATE, PRODUCT_DELETE
- WAREHOUSE_CREATE, WAREHOUSE_READ, WAREHOUSE_UPDATE, WAREHOUSE_DELETE
- TRANSACTION_CREATE, TRANSACTION_READ, TRANSACTION_UPDATE, TRANSACTION_DELETE
```

### Multi-Factor Authentication

- **TOTP Support**: Time-based one-time passwords
- **Backup Codes**: Recovery codes for account access
- **QR Code Generation**: For authenticator app setup
- **SMS Support**: (Planned feature)

## 🧪 Testing

### Test Structure

```
src/test/java/
├── integration/                # Integration tests with TestContainers
├── controller/                 # Web layer tests with MockMvc
├── service/                    # Business logic unit tests
├── repository/                 # Data access tests with @DataJpaTest
└── util/                       # Utility test classes
```

### Running Tests

```bash
# Run all tests
./gradlew test

# Run specific test categories
./gradlew test --tests "*IntegrationTest"
./gradlew test --tests "*ControllerTest"
./gradlew test --tests "*ServiceTest"

# Run tests with coverage report
./gradlew test jacocoTestReport

# Run tests in continuous mode
./gradlew test --continuous
```

### Test Configuration

**application-test.properties**

```properties
# Test database (H2 in-memory)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=create-drop

# Test logging
logging.level.org.springframework.test=DEBUG
logging.level.org.testcontainers=INFO

# Test JWT settings
app.jwt.secret=test-secret-key-for-testing-only
app.jwt.expiration=3600000
```

### Integration Testing with TestContainers

```java
@SpringBootTest
@Testcontainers
class InventoryIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }
}
```

## 🚀 Deployment

### Docker Deployment

```bash
# Build Docker image
docker build -t synectix-backend:latest .

# Run with Docker Compose
docker-compose up -d
```

**Dockerfile**

```dockerfile
FROM openjdk:17-jdk-slim

# Create app directory
WORKDIR /app

# Copy application JAR
COPY build/libs/synectix-*.jar app.jar

# Expose port
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

# Run application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
```

### Environment Variables

```bash
# Database configuration
DATABASE_URL=jdbc:postgresql://postgres:5432/synectix
DATABASE_USERNAME=synectix_user
DATABASE_PASSWORD=synectix_password

# JWT configuration
JWT_SECRET=your-production-secret-key
JWT_EXPIRATION=86400000

# Application configuration
SPRING_PROFILES_ACTIVE=prod
SERVER_PORT=8080
```

### Production Configuration

**application-prod.properties**

```properties
# Database
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}

# JPA
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false

# Logging
logging.level.com.froilan.synectix=INFO
logging.level.org.springframework.security=WARN

# Actuator
management.endpoints.web.exposure.include=health,info
management.endpoint.health.show-details=when-authorized
```

## 🤝 Contributing

### Development Workflow

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature-name`
3. Make your changes and add tests
4. Run tests: `./gradlew test`
5. Commit your changes: `git commit -m "Add your feature"`
6. Push to the branch: `git push origin feature/your-feature-name`
7. Submit a pull request

### Code Style Guidelines

- Follow Google Java Style Guide
- Use meaningful variable and method names
- Add JavaDoc comments for public APIs
- Write unit tests for new functionality
- Maintain test coverage above 80%

### Commit Message Format

```
type(scope): description

[optional body]

[optional footer]
```

Examples:

```
feat(inventory): add product variant support
fix(auth): resolve JWT token expiration issue
docs(readme): update installation instructions
```

### Code Review Checklist

- [ ] Code follows style guidelines
- [ ] Tests are included and passing
- [ ] Documentation is updated
- [ ] No security vulnerabilities
- [ ] Performance impact considered
- [ ] Backward compatibility maintained

## 🛠 Troubleshooting

### Common Issues

#### Database Connection Issues

```bash
# Check if PostgreSQL is running
docker-compose ps postgres

# Check database logs
docker-compose logs postgres

# Reset database
docker-compose down -v
docker-compose up -d postgres
```

#### JWT Token Issues

```bash
# Verify JWT secret configuration
grep JWT_SECRET .env

# Check token expiration in logs
tail -f logs/application.log | grep JWT
```

#### Build Issues

```bash
# Clean and rebuild
./gradlew clean build

# Skip tests if needed
./gradlew build -x test

# Check dependency conflicts
./gradlew dependencies
```

#### Memory Issues

```bash
# Increase JVM memory
export JAVA_OPTS="-Xmx2g -Xms1g"

# Or in application.properties
server.tomcat.max-threads=200
```

### Debug Mode

```properties
# Enable debug logging
logging.level.com.froilan.synectix=DEBUG
logging.level.org.springframework.security=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

### Performance Monitoring

```bash
# Enable JMX
-Dcom.sun.management.jmxremote
-Dcom.sun.management.jmxremote.port=9999
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.ssl=false

# Access metrics
curl http://localhost:8080/actuator/metrics
curl http://localhost:8080/actuator/health
```

---

## 📧 Support

For questions, issues, or contributions, please:

- Open an issue on GitHub
- Contact the development team
- Check the project wiki for additional documentation

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Happy coding! 🚀**
