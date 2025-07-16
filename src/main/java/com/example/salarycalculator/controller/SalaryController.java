// =====================================================
// SALARY CONTROLLER - SPRING MVC CONTROLLER
// =====================================================
// This class handles all web requests related to salary calculations
// It processes salary calculation requests and displays results

package com.example.salarycalculator.controller;

// Import our custom entity and service classes
import com.example.salarycalculator.entity.Employee;
import com.example.salarycalculator.entity.TaxCalculation;
import com.example.salarycalculator.service.EmployeeService;
import com.example.salarycalculator.service.SalaryCalculationService;

// Spring Framework imports
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Salary Controller - Handles all salary calculation related web requests
 * 
 * This controller manages:
 * - Salary calculation for employees
 * - Display of salary and tax calculation results
 * - Retrieval of existing salary calculations
 * 
 * Spring MVC Flow for Salary Calculation:
 * 1. User submits employee ID for salary calculation
 * 2. Controller receives the request
 * 3. Controller calls service layer to perform calculations
 * 4. Results are passed to the view for display
 */
@Controller
public class SalaryController {
    
    /**
     * Dependency Injection - Spring automatically provides SalaryCalculationService
     * This service handles all the business logic for salary and tax calculations
     */
    @Autowired
    private SalaryCalculationService salaryCalculationService;
    
    /**
     * Dependency Injection - Spring automatically provides EmployeeService
     * This service handles all employee-related database operations
     */
    @Autowired
    private EmployeeService employeeService;
    
    /**
     * Calculate salary for an employee (POST method)
     * 
     * This method is called when a user submits the salary calculation form
     * It performs the following steps:
     * 1. Validates that the employee exists
     * 2. Calls the salary calculation service
     * 3. Displays the results
     * 
     * @param employeeId - The ID of the employee to calculate salary for
     * @param model - Spring Model object to pass data to the view
     * @return Template name ("salary-result" for success, "calculate-salary" for errors)
     */
    @PostMapping("/calculate-salary")
    public String calculateSalary(@RequestParam Long employeeId, Model model) {
        try {
            // Step 1: Get employee details from database
            // orElse(null) returns null if employee not found (instead of throwing exception)
            Employee employee = employeeService.getEmployeeById(employeeId).orElse(null);
            
            // Step 2: Validate that employee exists
            if (employee == null) {
                // Employee not found - add error message to model
                model.addAttribute("error", "Employee not found with ID: " + employeeId);
                return "calculate-salary";  // Return to the form with error
            }
            
            // Step 3: Calculate salary and tax using the service layer
            // This is where the business logic happens (tax calculations, deductions, etc.)
            TaxCalculation taxCalculation = salaryCalculationService.calculateSalary(employeeId);
            
            // Step 4: Add data to model for the view
            // The view will use these attributes to display the results
            model.addAttribute("employee", employee);
            model.addAttribute("taxCalculation", taxCalculation);
            
            // Step 5: Return the result template
            return "salary-result";  // Renders src/main/resources/templates/salary-result.html
            
        } catch (Exception e) {
            // Handle any unexpected errors during calculation
            model.addAttribute("error", "Error calculating salary: " + e.getMessage());
            return "calculate-salary";  // Return to form with error message
        }
    }
    
    /**
     * Get existing tax calculation for an employee (GET method)
     * 
     * This method allows users to view previously calculated salary details
     * It's useful for reviewing past calculations without recalculating
     * 
     * @param employeeId - Employee ID from the URL path (e.g., /salary/1)
     * @param model - Spring Model object to pass data to the view
     * @return Template name
     */
    @GetMapping("/salary/{employeeId}")
    public String getSalaryDetails(@PathVariable Long employeeId, Model model) {
        try {
            // Step 1: Get employee details from database
            Employee employee = employeeService.getEmployeeById(employeeId).orElse(null);
            
            // Step 2: Validate that employee exists
            if (employee == null) {
                model.addAttribute("error", "Employee not found with ID: " + employeeId);
                return "calculate-salary";
            }
            
            // Step 3: Get existing tax calculation from database
            // This retrieves a previously calculated salary/tax record
            TaxCalculation taxCalculation = salaryCalculationService.getTaxCalculationByEmployeeId(employeeId);
            
            // Step 4: Check if calculation exists
            if (taxCalculation == null) {
                model.addAttribute("error", "No salary calculation found for employee ID: " + employeeId);
                return "calculate-salary";
            }
            
            // Step 5: Add data to model for display
            model.addAttribute("employee", employee);
            model.addAttribute("taxCalculation", taxCalculation);
            
            // Step 6: Return the result template
            return "salary-result";  // Renders src/main/resources/templates/salary-result.html
            
        } catch (Exception e) {
            // Handle any unexpected errors during retrieval
            model.addAttribute("error", "Error retrieving salary details: " + e.getMessage());
            return "calculate-salary";
        }
    }
} 