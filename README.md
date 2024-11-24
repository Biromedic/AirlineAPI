# AirlineAPI

AirlineAPI is a RESTful web application designed to facilitate the management of flights, tickets, bookings, and user interactions in an airline ecosystem. Built with **Spring Boot**, it offers secure role-based access control and seamless integration with a database. The project adheres to **SOLID** principles and **clean code** standards for maintainability and scalability.

---

## 🚀 Features

- **Authentication & Authorization**: Secure JWT-based authentication with role-based access (`USER`, `ADMIN`).
- **Flight Management**: Add, view, and manage flights.
- **Ticket Booking**: Book tickets for flights and perform check-ins.
- **User Management**: Admin-only features for viewing and managing user data.
- **Listing and Reviews**: Flight listings with user-submitted reviews and ratings.
- **Swagger Integration**: Auto-generated API documentation for easy exploration.
- **Validation**: Request validation with clear error messages.

---

## 🛠️ Tech Stack

### Backend
- **Java 17**
- **Spring Boot 3.3.4**
  - Spring Security
  - Spring Data JPA
  - Spring Validation
- **PostgreSQL** for database management
- **Swagger** for API documentation
- **Lombok** for boilerplate code reduction


---

## 📊 ER Diagram

The ER diagram for the database schema can be found [here](https://github.com/Biromedic/AirlineAPI/blob/main/AirlineAPIER.png).

---

---

### Access the API documentation:

-	Swagger UI: https://airlineapi.onrender.com/swagger-ui/index.html

---

## 🛡️ Security

-	JWT-based Authentication: Login returns a JWT token that must be included in the Authorization header as Bearer <token> for protected routes.
    
-	Role-based Access: ADMIN and USER roles control access to endpoints.
