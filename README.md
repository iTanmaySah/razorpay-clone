# Razorpay Clone

A payment gateway built from scratch to understand the internal architecture of systems like Razorpay.

## Tech Stack

### Current

- Java 25
- Spring Boot
- PostgreSQL
- Maven
- JPA / Hibernate

### Planned

- Redis
- Kafka
- Docker
- Kubernetes
- Microservices

---

## Features

### Merchant Management

- Merchant onboarding
- API key generation
- Merchant authentication

### Order Management

- Create order
- Fetch order
- Order lifecycle management

### Payment Processing

- Payment initiation
- Payment authorization
- Payment capture
- Payment failure handling

### Refunds

- Full refunds
- Partial refunds
- Refund status tracking

### Webhooks

- Event generation
- Webhook delivery
- Retry mechanism
- Dead-letter handling

### Vault

- Secure storage of sensitive payment data
- Tokenization support

---

## Current Project Structure

src/main/java/com/program/razorpay

```text
com.program.razorpay
│
├── common
│   ├── entity
│   ├── dto
│   ├── exception
│   ├── config
│   └── util
│
├── merchant
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   └── mapper
│
├── order
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   └── mapper
│
├── payment
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   └── mapper
│
├── refund
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   └── mapper
│
├── webhook
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   └── dto
│
├── vault
│   ├── controller
│   ├── service
│   ├── repository
│   └── entity
│
└── RazorpayApplication
```

---

## Database

PostgreSQL

### Core Tables

- merchants
- orders
- payments
- refunds
- webhooks
- vault_tokens

---

## Planned Evolution

### Phase 1 - Monolith

```text
Merchant
Order
Payment
Refund
Webhook
Vault
```

Single Spring Boot application.

### Phase 2 - Event Driven

```text
Payment Completed
        ↓
      Kafka
        ↓
Webhook Service
Refund Service
Analytics Service
```

Introduction of:

- Kafka
- Redis
- Retry Queues

### Phase 3 - Containerization

```text
Application
    ↓
 Docker
```

Dockerfile and Docker Compose setup.

### Phase 4 - Kubernetes

```text
Pods
Services
Deployments
ConfigMaps
Secrets
Ingress
```

Deployment on Kubernetes cluster.

### Phase 5 - Microservices

```text
merchant-service
order-service
payment-service
refund-service
webhook-service
vault-service
```

Each service owns its:

- Database
- APIs
- Domain logic

---

## Running Locally

```bash
mvn spring-boot:run
```

---

## Status

🚧 Work in Progress

This project is being built incrementally to understand real-world payment gateway architecture and distributed systems design.