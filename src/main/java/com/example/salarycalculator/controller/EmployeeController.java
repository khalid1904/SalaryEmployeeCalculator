// =====================================================
// EMPLOYEE CONTROLLER - SPRING MVC CONTROLLER
// =====================================================
// This class handles all web requests related to employee operations
// Controllers in Spring MVC receive HTTP requests and return responses (usually HTML pages)

package com.example.salarycalculator.controller;

// Import our custom classes
import com.example.salarycalculator.entity.Employee;
import com.example.salarycalculator.service.EmployeeService;

// Spring Framework imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Employee Controller - Handles all employee-related web requests
 * 
 * This class is annotated with @Controller, which tells Spring that this class
 * is a web controller that can handle HTTP requests.
 * 
 * Spring MVC Flow:
 * 1. HTTP request comes to a URL (e.g., /register)
 * 2. Spring routes it to the appropriate method in this controller
 * 3. Method processes the request and returns a view name
 * 4. Spring renders the corresponding HTML template
 */
@Controller
public class EmployeeController {
    
    /**
     * Dependency Injection - Spring automatically provides an instance of EmployeeService
     * @Autowired tells Spring to inject (provide) an EmployeeService object
     * This is called "Dependency Injection" - Spring manages object creation for you
     */
    @Autowired
    private EmployeeService employeeService;
    
    /**
     * Home page handler
     * @GetMapping("/") - Maps HTTP GET requests to the root URL "/"
     * @return "index" - Returns the name of the Thymeleaf template to render
     */
    @GetMapping("/")
    public String home() {
        return "index";  // Renders src/main/resources/templates/index.html
    }
    
    /**
     * Shows the employee registration form
     * @GetMapping("/register") - Maps to GET /register
     * @param model - Spring provides this object to pass data to the view
     * @return "registration" - Renders the registration form template
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Create a new empty Employee object and pass it to the form
        // This allows the form to bind to the employee object
        model.addAttribute("employee", new Employee());
        return "registration";  // Renders src/main/resources/templates/registration.html
    }
    
    /**
     * Handles employee registration form submission
     * @PostMapping("/register") - Maps to POST /register (form submission)
     * @param employee - Spring automatically creates an Employee object from form data
     * @param model - For passing messages back to the view
     * @return "registration" - Returns to the registration page with success/error message
     */
    @PostMapping("/register")
    public String registerEmployee(@ModelAttribute Employee employee, Model model) {
        try {
            // Save the employee to the database using the service layer
            Employee savedEmployee = employeeService.saveEmployee(employee);
            
            // Add success message to the model (will be displayed in the view)
            model.addAttribute("message", "Employee registered successfully with ID: " + savedEmployee.getId());
            return "registration";
        } catch (Exception e) {
            // Add error message to the model if something goes wrong
            model.addAttribute("error", "Error registering employee: " + e.getMessage());
            return "registration";
        }
    }
    
    /**
     * Shows the employee details lookup form
     * @GetMapping("/employee-details") - Maps to GET /employee-details
     * @return "employee-details" - Renders the employee lookup form
     */
    @GetMapping("/employee-details")
    public String showEmployeeDetailsForm() {
        return "employee-details";  // Renders src/main/resources/templates/employee-details.html
    }
    
    /**
     * Handles employee details form submission (POST method)
     * @PostMapping("/employee-details") - Maps to POST /employee-details
     * @param id - Employee ID from the form (automatically converted from String to Long)
     * @param model - For passing employee data or error messages to the view
     * @return Template name based on whether employee was found
     */
    @PostMapping("/employee-details")
    public String handleEmployeeDetailsForm(@RequestParam Long id, Model model) {
        System.out.println("=== POST /employee-details called ===");
        System.out.println("Received request for employee ID: " + id);
        try {
            // Try to find the employee by ID
            // orElse(null) returns null if employee not found (instead of throwing exception)
            Employee employee = employeeService.getEmployeeById(id).orElse(null);
            
            if (employee != null) {
                // Employee found - log details and pass to view
                System.out.println("Found employee: " + employee.getName());
                System.out.println("Employee details - ID: " + employee.getId() + ", Name: " + employee.getName() + ", Age: " + employee.getAge() + ", CTC: " + employee.getMonthlyCtc());
                model.addAttribute("employee", employee);
                System.out.println("Returning employee-details-result template");
                return "employee-details-result";  // Show employee details
            } else {
                // Employee not found - show error message
                System.out.println("Employee not found with ID: " + id);
                model.addAttribute("error", "Employee not found with ID: " + id);
                return "employee-details";  // Return to the form with error
            }
        } catch (Exception e) {
            // Handle any other errors
            System.out.println("Error retrieving employee: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving employee: " + e.getMessage());
            return "employee-details";
        }
    }
    
    /**
     * Test method for employee details (GET with query parameter)
     * This method allows testing via URL like: /employee-details-test?id=1
     * @param id - Employee ID from URL query parameter
     * @param model - For passing data to view
     * @return Template name
     */
    @GetMapping("/employee-details-test")
    public String testEmployeeDetails(@RequestParam Long id, Model model) {
        System.out.println("=== TEST GET /employee-details-test called ===");
        System.out.println("TEST: Received GET request for employee ID: " + id);
        try {
            Employee employee = employeeService.getEmployeeById(id).orElse(null);
            if (employee != null) {
                System.out.println("TEST: Found employee: " + employee.getName());
                System.out.println("TEST: Employee details - ID: " + employee.getId() + ", Name: " + employee.getName() + ", Age: " + employee.getAge() + ", CTC: " + employee.getMonthlyCtc());
                model.addAttribute("employee", employee);
                return "employee-details-result";
            } else {
                System.out.println("TEST: Employee not found with ID: " + id);
                model.addAttribute("error", "Employee not found with ID: " + id);
                return "employee-details";
            }
        } catch (Exception e) {
            System.out.println("TEST: Error retrieving employee: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving employee: " + e.getMessage());
            return "employee-details";
        }
    }
    
    /**
     * Get employee details by ID (for direct URL access)
     * @PathVariable - Extracts the ID from the URL path
     * Example: /employee/1 will call this method with id=1
     * @param id - Employee ID from URL path
     * @param model - For passing data to view
     * @return Template name
     */
    @GetMapping("/employee/{id}")
    public String getEmployeeDetails(@PathVariable Long id, Model model) {
        try {
            Employee employee = employeeService.getEmployeeById(id).orElse(null);
            if (employee != null) {
                model.addAttribute("employee", employee);
                return "employee-details-result";
            } else {
                model.addAttribute("error", "Employee not found with ID: " + id);
                return "employee-details";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error retrieving employee: " + e.getMessage());
            return "employee-details";
        }
    }
    
    /**
     * Shows the salary calculation form
     * @GetMapping("/calculate-salary") - Maps to GET /calculate-salary
     * @return "calculate-salary" - Renders the salary calculation form
     */
    @GetMapping("/calculate-salary")
    public String showSalaryCalculationForm() {
        return "calculate-salary";  // Renders src/main/resources/templates/calculate-salary.html
    }
} 