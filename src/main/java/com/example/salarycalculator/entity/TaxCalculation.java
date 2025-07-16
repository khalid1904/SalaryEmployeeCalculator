package com.example.salarycalculator.entity;

import javax.persistence.*;

@Entity
@Table(name = "taxes")
public class TaxCalculation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "employee_id")
    private Long employeeId;
    
    @Column(name = "annual_ctc")
    private Double annualCtc;
    
    @Column(name = "deductions")
    private Double deductions;
    
    @Column(name = "taxable_income")
    private Double taxableIncome;
    
    @Column(name = "surcharge")
    private Double surcharge;
    
    @Column(name = "cess")
    private Double cess;
    
    @Column(name = "net_payable_tax")
    private Double netPayableTax;
    
    @Column(name = "take_home_salary")
    private Double takeHomeSalary;
    
    // Default constructor
    public TaxCalculation() {}
    
    // Constructor with fields
    public TaxCalculation(Long employeeId, Double annualCtc, Double deductions, 
                        Double taxableIncome, Double surcharge, Double cess, 
                        Double netPayableTax, Double takeHomeSalary) {
        this.employeeId = employeeId;
        this.annualCtc = annualCtc;
        this.deductions = deductions;
        this.taxableIncome = taxableIncome;
        this.surcharge = surcharge;
        this.cess = cess;
        this.netPayableTax = netPayableTax;
        this.takeHomeSalary = takeHomeSalary;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    
    public Double getAnnualCtc() {
        return annualCtc;
    }
    
    public void setAnnualCtc(Double annualCtc) {
        this.annualCtc = annualCtc;
    }
    
    public Double getDeductions() {
        return deductions;
    }
    
    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }
    
    public Double getTaxableIncome() {
        return taxableIncome;
    }
    
    public void setTaxableIncome(Double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }
    
    public Double getSurcharge() {
        return surcharge;
    }
    
    public void setSurcharge(Double surcharge) {
        this.surcharge = surcharge;
    }
    
    public Double getCess() {
        return cess;
    }
    
    public void setCess(Double cess) {
        this.cess = cess;
    }
    
    public Double getNetPayableTax() {
        return netPayableTax;
    }
    
    public void setNetPayableTax(Double netPayableTax) {
        this.netPayableTax = netPayableTax;
    }
    
    public Double getTakeHomeSalary() {
        return takeHomeSalary;
    }
    
    public void setTakeHomeSalary(Double takeHomeSalary) {
        this.takeHomeSalary = takeHomeSalary;
    }
} 