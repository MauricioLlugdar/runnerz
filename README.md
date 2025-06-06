# Running Tracker API

This is a Java-based REST API built with Spring Boot and Maven for tracking running sessions. It provides CRUD operations to manage running records and integrates external API communication to enrich user data.

## Technologies Used

- Java  
- Spring Boot  
- Maven  
- PostgreSQL  
- Docker & Docker Compose  
- Testcontainers  
- H2 Database (for in-memory persistence in earlier stages)  
- REST Client & HttpClient  
- JSONPlaceholder (external API for user simulation)  

## Project Structure and Branch Evolution

The development of the project is organized into multiple branches, each representing a different phase or feature set:

### `withoutDB`
Initial version using static in-memory data. All running session data is stored in Java objects without any database.

### `inMemDbH2`
Introduced H2 in-memory database to temporarily persist data during runtime.

### `commDatabaseHomemadeCRUD`
Configured a PostgreSQL database using Docker and `docker-compose.yml`. Implemented a custom CRUD system to handle database operations manually.

### `RestCommunicationRC`
Added external API integration using Spring’s `RestClient` to fetch user data from [JSONPlaceholder](https://jsonplaceholder.typicode.com).

### `main`
Current branch. Uses `UserHttpClient` for external API calls and introduces integration testing using Testcontainers with Docker-based environments.

## How to Run

1. Make sure you have **Docker**, **Java**, and **Maven** installed.
2. Clone the repository and switch to the desired branch.
3. If you're using the PostgreSQL-based version (`commDatabaseHomemadeCRUD` or `main`), make sure the database is running. You can either:
   - Start it manually using:
     ```bash
     docker-compose up -d
     ```
   - Or rely on your Spring Boot configuration if it automatically manages the container (e.g., using Testcontainers or a Docker Compose integration).

4. Run the application:
   ```bash
   ./mvnw spring-boot:run
