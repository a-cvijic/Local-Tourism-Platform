# Local Tourism Promotion Platform 🌐

## Overview

This platform aims to showcase local tourism attractions. It leverages a suite of microservices architecture for modularity and scalability. Currently, the platform comprises the **Attractions Service** microservice with plans to expand with the **Tours Service** and **Events Service**.

## Technologies

The microservices are built using diverse, modern technologies tailored to their specific needs:

- **Spring Boot** with **Java** for the Attractions Service backend.
- **Quarkus** with **gRPC** for the Tours Service.
- **Quarkus** with reactive programming and **ActiveMQ** for the Events Service.
- **MySQL** for data persistence across services.
- **Docker** for containerization.

## System Architecture 🏗️

The platform operates on a microservices architecture, ensuring each service can scale independently based on demand.

### Microservices

- **Attractions Service (Java/Spring Boot):** Manages data related to tourist attractions.
- **Tours Service (Java/Quarkus):** Manages detailed information about various tours offered.
- **Events Service (Java/Quarkus):** Handles event listings and attendee management, leveraging a reactive programming model and message brokering for high performance and responsiveness.

### Web Application

- _Details to be provided in future iterations._

## Getting Started 🚀

Clone the repository to your local machine to begin development:

```bash
git clone https://github.com/a-cvijic/Local-Tourism-Platform.git
cd attractions
```

## Attractions Service Setup

Navigate to the Attractions Service directory:

bash

```bash
cd attractions
```

## Building 🏗

Compile the service using Maven:

```bash
mvn clean install
```

## Running 🏃‍♂️

Launch the service with Spring Boot:

```bash
mvn spring-boot:run
```

## Database Setup 🗃

A PostgreSQL database is used. Follow the instructions in the service's application.properties for setup.

## Testing 🧪

Execute tests with Maven:

```bash
mvn test
```

## API Documentation 📄

The Swagger UI is available at http://localhost:8080/swagger-ui.html for an interactive API documentation and testing experience.
