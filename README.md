# Email OTP Authentication System 🔐

A professional, full-stack mobile application featuring a robust **Spring Boot** backend and a sleek **Flutter** frontend. This system implements a secure, OTP-based (One-Time Password) authentication flow, including user registration, secure login with JWT, and a comprehensive "Forgot Password" mechanism.

---

## 🚀 Overview
The **Email OTP Authentication System** is designed for modern security needs. It ensures that users are verified via their email addresses before accessing protected resources. The system follows industry standards for password validation, data encryption, and session management.

---

## 🏗️ System Architecture
The project follows a **Monorepo** structure, separating the concerns of backend and frontend while maintaining a unified repository for easier management.

- **Backend:** RESTful API built with Spring Boot.
- **Frontend:** Cross-platform mobile Application built with Flutter.
- **Security:** State-of-the-art security with JWT (JSON Web Tokens) and OTP verification.

---

## 💻 Tech Stack

### **Backend (Server Side)**
*   **Language:** Java 17+
*   **Framework:** Spring Boot 3.x
*   **Security:** Spring Security (JWT Authentication)
*   **Database:** MySQL (Relational Database)
*   **Data Access:** Spring Data JPA (Hibernate)
*   **Mailing:** Spring Boot Starter Mail (SMTP Integration)
*   **Build Tool:** Maven

### **Frontend (Mobile Client)**
*   **Language:** Dart
*   **Framework:** Flutter
*   **Networking:** HTTP Package
*   **Persistence:** Shared Preferences (for JWT storage)
*   **Styling:** Custom Modern UI with Material Design

---

## 🌟 Key Features
- **OTP-based Registration:** Users receive a 6-digit OTP via email to verify their account.
- **Secure Login:** Post-verification, users log in to receive a JWT for authorized access.
- **Token-based Authentication:** Protected routes (like User Profile) require a valid JWT.
- **Complex Password Validation:** Includes Regex-based checks (Minimum 8 chars, Uppercase, Lowercase, Number, Special Character).
- **Forgot Password Flow:** Secure OTP-based password reset mechanism.
- **Responsive UI:** Modern design with glassmorphism and gradient aesthetics.

---

## 📂 Project Structure
```text
EmailOtpApp/
├── backend/                # Spring Boot REST API
│   ├── src/main/java       # Source code (Controllers, Services, Models)
│   ├── src/main/resources  # Application properties and configurations
│   └── pom.xml             # Maven dependencies
└── frontend/               # Flutter Mobile App
    ├── lib/                # Source code (Screens, Services, Utils)
    ├── pubspec.yaml        # Flutter dependencies
    └── assets/             # UI assets (if any)
```

---

## ⚙️ Setup & Installation

### **1. Backend Setup**
1.  Navigate to the `backend/` directory.
2.  Update `src/main/resources/application.properties` with your MySQL credentials and Gmail SMTP settings.
3.  Run the application using Maven:
    ```bash
    ./mvnw spring-boot:run
    ```

### **2. Frontend Setup**
1.  Navigate to the `frontend/` directory.
2.  Install dependencies:
    ```bash
    flutter pub get
    ```
3.  Update the `baseUrl` in `lib/utils/api_constants.dart` to match your local IP (use `10.0.2.2` for Android Emulator).
4.  Run the app:
    ```bash
    flutter run
    ```

---

## 🔒 Security Best Practices
- **Password Hashing:** Passwords are encrypted using BCrypt before being stored.
- **OTP Expiration:** OTP tokens are valid only for 5 minutes.
- **Stateless Auth:** JWT ensures scalable and secure user sessions.
- **Input Validation:** Strict server-side validation for all user inputs.

---

## 👨‍💻 Developer
Developed by **Alpit Gadhave**.

---

## 📝 License
This project is for educational purposes and follows standard open-source practices.
