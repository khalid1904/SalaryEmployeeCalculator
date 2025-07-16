package com.example.salarycalculator.service;

import com.example.salarycalculator.entity.Employee;
import com.example.salarycalculator.entity.TaxCalculation;
import com.example.salarycalculator.repository.EmployeeRepository;
import com.example.salarycalculator.repository.TaxCalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SalaryCalculationService {
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private TaxCalculationRepository taxCalculationRepository;
    
    // Calculate salary and tax for an employee
    public TaxCalculation calculateSalary(Long employeeId) {
        Optional<Employee> employeeOpt = employeeRepository.findById(employeeId);
        
        if (!employeeOpt.isPresent()) {
            throw new RuntimeException("Employee not found with ID: " + employeeId);
        }
        
        Employee employee = employeeOpt.get();
        
        // Calculate annual CTC
        double annualCtc = employee.getMonthlyCtc() * 12.0;
        
        // Standard deduction
        double deductions = 50000.0;
        
        // Taxable income
        double taxableIncome = annualCtc - deductions;
        
        // Calculate tax based on Indian tax slabs
        double tax = calculateTax(taxableIncome);
        
        // Calculate surcharge
        double surcharge = calculateSurcharge(taxableIncome, tax);
        
        // Calculate health and education cess
        double cess = calculateCess(taxableIncome, tax);
        
        // Net payable tax
        double netPayableTax = tax + surcharge + cess;
        
        // Take home salary
        double takeHomeSalary = taxableIncome - netPayableTax;
        
        // Create and save tax calculation
        TaxCalculation taxCalculation = new TaxCalculation(
            employeeId, annualCtc, deductions, taxableIncome, 
            surcharge, cess, netPayableTax, takeHomeSalary
        );
        
        return taxCalculationRepository.save(taxCalculation);
    }
    
    // Calculate tax based on Indian tax slabs
    private double calculateTax(double taxableIncome) {
        if (taxableIncome <= 250000) {
            return 0;
        } else if (taxableIncome <= 500000) {
            return 0.05 * (taxableIncome - 250000);
        } else if (taxableIncome <= 1000000) {
            return 12500 + (0.2 * (taxableIncome - 500000));
        } else {
            return 112500 + (0.3 * (taxableIncome - 1000000));
        }
    }
    
    // Calculate surcharge
    private double calculateSurcharge(double taxableIncome, double tax) {
        if (taxableIncome >= 5000000 && taxableIncome <= 10000000) {
            return 0.1 * tax;
        } else if (taxableIncome > 10000000 && taxableIncome <= 20000000) {
            return 0.15 * tax;
        } else if (taxableIncome > 20000000 && taxableIncome <= 50000000) {
            return 0.25 * tax;
        } else if (taxableIncome > 50000000) {
            return 0.37 * tax;
        }
        return 0;
    }
    
    // Calculate health and education cess
    private double calculateCess(double taxableIncome, double tax) {
        if (taxableIncome > 500000) {
            return 0.04 * tax;
        }
        return 0;
    }
    
    // Get tax calculation by employee ID
    public TaxCalculation getTaxCalculationByEmployeeId(Long employeeId) {
        return taxCalculationRepository.findByEmployeeId(employeeId);
    }
} 