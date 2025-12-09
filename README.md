# PawPalsApi

## 📌 Descripción del Proyecto
PawPalsApi es el backend de **PawPals**, una red social diseñada para dueños de perros. Permite gestionar usuarios, perros, seguimiento entre mascotas, paseos, participación en paseos y autenticación mediante JWT.  
Está desarrollado con **Spring Boot 3**, **Java 23** y **PostgreSQL**, siguiendo una arquitectura REST.

## 🚀 Tecnologías Utilizadas
- Java 23
- Spring Boot 3.5+
- Spring Web
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- Lombok
- ModelMapper
- Maven

## 📂 Estructura del Proyecto
```
src/
 ├── controller/
 ├── service/
 ├── repository/
 ├── model/
 ├   └── dto/
 ├── config/
 ├── exception/
 ├── security/
 └── PawPalsApiApplication.java
```

## 🔐 Autenticación
JWT con rutas públicas:
- POST /pawpalsapi/auth/token
- POST /pawpalsapi/users


## 🛠️ Endpoints Principales

### Auth
POST /pawpalsapi/auth/token

### Users
POST /users  
GET /users/me  
GET /users/{id}  
PATCH /users/{id}  
DELETE /users/{id}

### Dogs
POST /users/{userId}/dogs  
GET /users/{userId}/dogs  
GET /dogs/{dogId}  
PATCH /dogs/{dogId}  
DELETE /dogs/{dogId}  
GET /dogs/search?name=  
GET /dogs/{dogId}/walks

### Follows
GET /users/{userId}/followed  
POST /users/{userId}/follow-dog/{dogId}  
DELETE /users/{userId}/follow-dog/{dogId}

### Walks
POST /users/{userId}/walks  
GET /users/{userId}/walks  
GET /walks  
GET /walks/upcoming  
GET /walks/{walkId}  
PATCH /walks/{walkId}  
POST /walks/{walkId}/cancel  
GET /walks/{walkId}/summary

### Participación en Paseos
POST /walks/{walkId}/dogs  
GET /walks/{walkId}/dogs  
PATCH /walks/update-participation/{walkDogId}  
DELETE /walks/remove-dog/{walkDogId}  
GET /users/{userId}/walks/joined

## 🧪 Ejecución del Proyecto
```
mvn spring-boot:run
```

## 🐳 Docker
```
docker compose up --build
```

