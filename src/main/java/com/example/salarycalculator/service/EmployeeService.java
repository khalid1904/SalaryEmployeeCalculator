// =====================================================
// EMPLOYEE SERVICE - BUSINESS LOGIC LAYER
// =====================================================
// This class contains the business logic for employee operations
// Services in Spring Boot handle the application's business rules
// They act as an intermediary between controllers and repositories

package com.example.salarycalculator.service;

// Import our custom entity and repository classes
import com.example.salarycalculator.entity.Employee;
import com.example.salarycalculator.repository.EmployeeRepository;

// Spring Framework imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Java utility imports
import java.util.List;
import java.util.Optional;

/**
 * Employee Service - Business logic layer for employee operations
 * 
 * This class is annotated with @Service, which tells Spring that this class
 * is a service component that contains business logic.
 * 
 * Service Layer Responsibilities:
 * 1. Contains business logic and rules
 * 2. Validates data before saving to database
 * 3. Handles complex operations that involve multiple repositories
 * 4. Provides a clean interface for controllers
 * 
 * Spring Architecture Layers:
 * Controller → Service → Repository → Database
 * 
 * Benefits of Service Layer:
 * - Separation of concerns
 * - Reusable business logic
 * - Easier testing
 * - Transaction management
 */
@Service  // Tells Spring this is a service component
public class EmployeeService {
    
    /**
     * Dependency Injection - Spring automatically provides EmployeeRepository
     * @Autowired tells Spring to inject (provide) an EmployeeRepository object
     * This repository handles all database operations for Employee entities
     */
    @Autowired
    private EmployeeRepository employeeRepository;
    
    /**
     * Save a new employee to the database
     * 
     * This method handles the business logic for creating a new employee:
     * 1. Validates the employee data (could add validation here)
     * 2. Saves the employee to the database
     * 3. Returns the saved employee with generated ID
     * 
     * @param employee - The employee object to save
     * @return Employee - The saved employee with generated ID
     */
    public Employee saveEmployee(Employee employee) {
        // The repository.save() method:
        // - If employee has no ID: creates a new record
        // - If employee has ID: updates existing record
        return employeeRepository.save(employee);
    }
    
    /**
     * Get all employees from the database
     * 
     * This method retrieves all employee records from the database.
     * Useful for displaying employee lists or generating reports.
     * 
     * @return List<Employee> - List of all employees
     */
    public List<Employee> getAllEmployees() {
        // repository.findAll() returns all records from the employees table
        return employeeRepository.findAll();
    }
    
    /**
     * Get employee by ID
     * 
     * This method retrieves a specific employee by their unique ID.
     * Returns an Optional, which is a safe way to handle potentially null values.
     * 
     * Optional benefits:
     * - Prevents NullPointerException
     * - Provides methods like isPresent(), orElse(), etc.
     * - Makes the code more readable and safer
     * 
     * @param id - The unique ID of the employee
     * @return Optional<Employee> - Employee if found, empty Optional if not found
     */
    public Optional<Employee> getEmployeeById(Long id) {
        // repository.findById() returns Optional<Employee>
        // - If employee exists: Optional containing the employee
        // - If employee doesn't exist: Empty Optional
        return employeeRepository.findById(id);
    }
    
    /**
     * Delete employee by ID
     * 
     * This method removes an employee from the database.
     * The employee is permanently deleted and cannot be recovered.
     * 
     * @param id - The unique ID of the employee to delete
     */
    public void deleteEmployee(Long id) {
        // repository.deleteById() removes the employee with the specified ID
        // If the employee doesn't exist, this method does nothing (no error)
        employeeRepository.deleteById(id);
    }
} 