package com.example.salarycalculator.controller;

import com.example.salarycalculator.entity.Employee;
import com.example.salarycalculator.entity.TaxCalculation;
import com.example.salarycalculator.service.EmployeeService;
import com.example.salarycalculator.service.SalaryCalculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SalaryController {
    
    @Autowired
    private SalaryCalculationService salaryCalculationService;
    
    @Autowired
    private EmployeeService employeeService;
    
    // Calculate salary for an employee
    @PostMapping("/calculate-salary")
    public String calculateSalary(@RequestParam Long employeeId, Model model) {
        try {
            // Get employee details
            Employee employee = employeeService.getEmployeeById(employeeId).orElse(null);
            if (employee == null) {
                model.addAttribute("error", "Employee not found with ID: " + employeeId);
                return "calculate-salary";
            }
            
            // Calculate salary and tax
            TaxCalculation taxCalculation = salaryCalculationService.calculateSalary(employeeId);
            
            // Add data to model
            model.addAttribute("employee", employee);
            model.addAttribute("taxCalculation", taxCalculation);
            
            return "salary-result";
            
        } catch (Exception e) {
            model.addAttribute("error", "Error calculating salary: " + e.getMessage());
            return "calculate-salary";
        }
    }
    
    // Get existing tax calculation for an employee
    @GetMapping("/salary/{employeeId}")
    public String getSalaryDetails(@PathVariable Long employeeId, Model model) {
        try {
            Employee employee = employeeService.getEmployeeById(employeeId).orElse(null);
            if (employee == null) {
                model.addAttribute("error", "Employee not found with ID: " + employeeId);
                return "calculate-salary";
            }
            
            TaxCalculation taxCalculation = salaryCalculationService.getTaxCalculationByEmployeeId(employeeId);
            if (taxCalculation == null) {
                model.addAttribute("error", "No salary calculation found for employee ID: " + employeeId);
                return "calculate-salary";
            }
            
            model.addAttribute("employee", employee);
            model.addAttribute("taxCalculation", taxCalculation);
            
            return "salary-result";
            
        } catch (Exception e) {
            model.addAttribute("error", "Error retrieving salary details: " + e.getMessage());
            return "calculate-salary";
        }
    }
} 