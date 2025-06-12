# 🛒 E-Commerce Microservices Project

A full-featured e-commerce backend built with Spring Boot Microservices architecture, secured using **Keycloak (OAuth 2.0 + RBAC)**, with **Kafka for messaging**, **MongoDB** for persistence, **OpenFeign** for inter-service communication, and **Docker** for containerization.

---

## 📦 Microservices Architecture

This project follows a modular microservices-based approach:

| Microservice       | Responsibility                          | Port  |
|--------------------|------------------------------------------|-------|
| `product-ms-1`     | Manages product catalog                  | 8081  |
| `inventory-ms-2`   | Manages stock for each product           | 8082  |
| `order-ms-3`       | Handles order placement & notifications  | 8083  |
| `notification-ms`  | Sends order confirmation via Kafka       | 8084  |
| `api-gateway-ms-5` | Gateway to route & secure endpoints      | 8085  |

---

## ⚙️ Tech Stack

- **Spring Boot**
- **Spring Security** + **OAuth 2.0**
- **Keycloak** (Authentication + Role-Based Access Control)
- **Spring Cloud OpenFeign** (Inter-Service Communication)
- **Spring WebFlux Security** (for non-blocking security config)
- **Kafka** (Asynchronous messaging)
- **MongoDB** (NoSQL database)
- **Docker** (Containerization)
- **Postman** (API Testing)

---

## 🔐 Security with Keycloak + OAuth 2.0

- **Authentication Provider**: Keycloak
- **Roles Defined**:
  - `ADMIN`: Full access (product, inventory, orders)
  - `USER`: Limited to viewing products and placing orders
- **Token Flow**:
  1. Users authenticate with username/password via Keycloak
  2. Keycloak returns a JWT (Bearer Token)
  3. Token is attached to each API request
  4. Spring Security uses token and authorizes routes accordingly

```yaml
# application.yml (Gateway example)
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: http://localhost:8080/realms/capstone_store
          jwk-set-uri: http://localhost:8080/realms/capstone_store/protocol/openid-connect/certs
