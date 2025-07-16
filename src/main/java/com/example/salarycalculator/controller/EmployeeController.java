package com.example.salarycalculator.controller;

import com.example.salarycalculator.entity.Employee;
import com.example.salarycalculator.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    // Show home page
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    // Show employee registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "registration";
    }
    
    // Handle employee registration
    @PostMapping("/register")
    public String registerEmployee(@ModelAttribute Employee employee, Model model) {
        try {
            Employee savedEmployee = employeeService.saveEmployee(employee);
            model.addAttribute("message", "Employee registered successfully with ID: " + savedEmployee.getId());
            return "registration";
        } catch (Exception e) {
            model.addAttribute("error", "Error registering employee: " + e.getMessage());
            return "registration";
        }
    }
    
    // Show employee details form
    @GetMapping("/employee-details")
    public String showEmployeeDetailsForm() {
        return "employee-details";
    }
    
    // Handle employee details form submission
    @PostMapping("/employee-details")
    public String handleEmployeeDetailsForm(@RequestParam Long id, Model model) {
        System.out.println("=== POST /employee-details called ===");
        System.out.println("Received request for employee ID: " + id);
        try {
            Employee employee = employeeService.getEmployeeById(id).orElse(null);
            if (employee != null) {
                System.out.println("Found employee: " + employee.getName());
                System.out.println("Employee details - ID: " + employee.getId() + ", Name: " + employee.getName() + ", Age: " + employee.getAge() + ", CTC: " + employee.getMonthlyCtc());
                model.addAttribute("employee", employee);
                System.out.println("Returning employee-details-result template");
                return "employee-details-result";
            } else {
                System.out.println("Employee not found with ID: " + id);
                model.addAttribute("error", "Employee not found with ID: " + id);
                return "employee-details";
            }
        } catch (Exception e) {
            System.out.println("Error retrieving employee: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Error retrieving employee: " + e.getMessage());
            return "employee-details";
        }
    }
    
    // Test method for employee details (GET with query parameter)
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
    
    // Get employee details by ID (for direct URL access)
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
    
    // Show salary calculation form
    @GetMapping("/calculate-salary")
    public String showSalaryCalculationForm() {
        return "calculate-salary";
    }
} 