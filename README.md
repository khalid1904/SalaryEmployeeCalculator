# Salary Employee Calculator

A modern web application for registering employees, calculating their salary (with Indian tax slabs), and viewing employee details. Built with Spring Boot, Thymeleaf, JPA, and MySQL.

## Features
- Employee registration
- Salary calculation with Indian tax slabs
- View employee details
- Persistent data storage (MySQL)
- Responsive, user-friendly UI

## Tech Stack
- Java 8+
- Spring Boot 2.7.x
- Thymeleaf
- Spring Data JPA (Hibernate)
- MySQL
- Gradle

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/khalid1904/SalaryEmployeeCalculator.git
cd SalaryEmployeeCalculator
```

### 2. Configure MySQL
- Ensure MySQL is running.
- The app uses database name `employeesalary` by default (auto-created if not present).
- Default credentials: `root` with no password. Update `src/main/resources/application.properties` if needed.

### 3. Build and Run
```bash
./gradlew bootRun
```
- The app will start on [http://localhost:8080](http://localhost:8080)

### 4. Access the App
- Home: [http://localhost:8080](http://localhost:8080)
- Register Employee: `/register`
- Calculate Salary: `/calculate-salary`
- View Employee Details: `/employee-details`

## Usage
- Register a new employee.
- Calculate salary and tax for an employee.
- View employee details by ID.

## Contribution
Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](LICENSE) 