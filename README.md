# Smart EV Energy Management & Dynamic Charging Optimization Platform

A Java-based electric vehicle (EV) charging management platform that dynamically allocates available charging power based on vehicle priority and site grid constraints. The application provides a web dashboard, REST APIs, and PostgreSQL-backed simulation history.

## Features

- **Dynamic Power Allocation:** Allocates available charging power according to EV priority and maximum charging limits.
- **Grid Capacity Management:** Accounts for building load before determining the power available for EV charging.
- **EV Energy Requirements:** Models battery capacity, current state of charge (SOC), target SOC, and maximum charging power.
- **Charging Simulation:** Accepts custom site and EV inputs through REST APIs and the web dashboard.
- **Simulation History:** Persists allocation records in PostgreSQL.
- **Interactive API Documentation:** Uses Swagger UI / OpenAPI to explore and execute API requests.
- **Web Dashboard:** Provides power configuration, simulation inputs, allocation results, and history.

## Technology Stack

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA / Hibernate
- PostgreSQL
- Maven
- HTML, CSS, JavaScript
- Swagger UI / OpenAPI

## System Overview

1. The user supplies the site grid capacity and building load.
2. The application calculates the available charging power.
3. The scheduler orders EVs by priority and allocates power within each vehicle's charging limit.
4. The API returns the allocation results.
5. Simulation records are stored in PostgreSQL and can be retrieved through the history endpoint.

**Power constraint:**

`Building Load + Total EV Charging Power ≤ Site Grid Capacity`

When building load leaves no available capacity, the scheduler does not allocate positive charging power.

## Main API Endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/scheduler/test` | Check the scheduler endpoint |
| GET | `/api/scheduler/simulate?buildingLoadKW=...` | Run the standard simulation |
| POST | `/api/scheduler/simulate/custom` | Run a custom simulation |
| POST | `/api/scheduler/simulate/custom/save` | Run and save a custom simulation |
| GET | `/api/scheduler/history` | Retrieve saved simulation history |
| GET | `/v3/api-docs` | Retrieve OpenAPI documentation |
| GET | `/swagger-ui/index.html` | Open interactive API documentation |

Additional EV endpoints are available under `/api/ev`.

## Getting Started

### Prerequisites

- Java 21
- Maven
- PostgreSQL
- IntelliJ IDEA or another Java IDE

### Database Setup

Create a PostgreSQL database:

```sql
CREATE DATABASE ev_energy_db;
```

Configure `src/main/resources/application.properties` with your local PostgreSQL credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ev_energy_db
spring.datasource.username=postgres
spring.datasource.password=YOUR_LOCAL_PASSWORD
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
```

Replace `YOUR_LOCAL_PASSWORD` with your own local database password. Do not commit real passwords or secrets to GitHub.

### Run the Application

1. Clone the repository.
2. Configure PostgreSQL and the application properties.
3. Open the project in IntelliJ IDEA and reload Maven dependencies.
4. Run the Spring Boot main application class.
5. Open the dashboard at `http://localhost:8080/`.
6. Open Swagger UI at `http://localhost:8080/swagger-ui/index.html`.

## Project Scope

This project simulates charging power allocation in software. It does not directly control physical EV chargers or establish real-time hardware communication.

## Future Improvements

- Support multiple EVs in the dashboard in one simulation.
- Add charts for charging allocation and site power utilization.
- Add authentication and role-based access control.
- Explore renewable energy availability and time-of-use electricity pricing.

## Author

Developed as a Java, Spring Boot, and PostgreSQL project focused on EV charging optimization and energy management.
