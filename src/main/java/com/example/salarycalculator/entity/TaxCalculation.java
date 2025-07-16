// =====================================================
// TAX CALCULATION ENTITY - JPA DATABASE MAPPING
// =====================================================
// This class represents a tax calculation record in the database
// It stores the results of salary and tax calculations for each employee
// Each record contains the complete breakdown of salary components and tax calculations

package com.example.salarycalculator.entity;

// JPA (Java Persistence API) imports
// These annotations tell JPA how to map this class to the database
import javax.persistence.*;

/**
 * Tax Calculation Entity - Represents a tax calculation record in the database
 * 
 * This entity stores the complete breakdown of an employee's salary calculation including:
 * - Annual Cost to Company (CTC)
 * - Various deductions
 * - Taxable income
 * - Tax components (surcharge, cess)
 * - Final take-home salary
 * 
 * Database Table Structure:
 * - id: Primary key (auto-generated)
 * - employee_id: Foreign key to employees table
 * - annual_ctc: Annual cost to company
 * - deductions: Total deductions
 * - taxable_income: Income after deductions
 * - surcharge: Additional tax surcharge
 * - cess: Additional cess
 * - net_payable_tax: Total tax payable
 * - take_home_salary: Final salary after all deductions
 */
@Entity  // Tells JPA this class should be mapped to a database table
@Table(name = "taxes")  // Specifies the database table name
public class TaxCalculation {
    
    /**
     * Primary Key - Unique identifier for each tax calculation record
     * @Id - Marks this field as the primary key
     * @GeneratedValue - Tells JPA to automatically generate values
     * GenerationType.IDENTITY - Uses database auto-increment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Employee ID - Foreign key reference to the employees table
     * Links this tax calculation to a specific employee
     * @Column - Maps this field to a database column
     */
    @Column(name = "employee_id")
    private Long employeeId;
    
    /**
     * Annual Cost to Company (CTC)
     * Total annual salary package before any deductions
     * @Column - Maps this field to a database column
     */
    @Column(name = "annual_ctc")
    private Double annualCtc;
    
    /**
     * Total Deductions
     * Sum of all applicable deductions (standard deduction, HRA, etc.)
     * @Column - Maps this field to a database column
     */
    @Column(name = "deductions")
    private Double deductions;
    
    /**
     * Taxable Income
     * Income after deducting all applicable deductions
     * This is the amount on which tax is calculated
     * @Column - Maps this field to a database column
     */
    @Column(name = "taxable_income")
    private Double taxableIncome;
    
    /**
     * Surcharge
     * Additional tax levied on high-income earners
     * @Column - Maps this field to a database column
     */
    @Column(name = "surcharge")
    private Double surcharge;
    
    /**
     * Cess
     * Additional tax for specific purposes (e.g., education cess)
     * @Column - Maps this field to a database column
     */
    @Column(name = "cess")
    private Double cess;
    
    /**
     * Net Payable Tax
     * Total tax amount that needs to be paid
     * Includes basic tax + surcharge + cess
     * @Column - Maps this field to a database column
     */
    @Column(name = "net_payable_tax")
    private Double netPayableTax;
    
    /**
     * Take Home Salary
     * Final salary after deducting all taxes
     * This is what the employee actually receives
     * @Column - Maps this field to a database column
     */
    @Column(name = "take_home_salary")
    private Double takeHomeSalary;
    
    // =====================================================
    // CONSTRUCTORS
    // =====================================================
    
    /**
     * Default constructor - Required by JPA
     * JPA needs a no-argument constructor to create entity instances
     */
    public TaxCalculation() {}
    
    /**
     * Constructor with all fields
     * Useful for creating TaxCalculation objects with complete data
     * 
     * @param employeeId - ID of the employee
     * @param annualCtc - Annual cost to company
     * @param deductions - Total deductions
     * @param taxableIncome - Taxable income after deductions
     * @param surcharge - Additional surcharge
     * @param cess - Additional cess
     * @param netPayableTax - Total tax payable
     * @param takeHomeSalary - Final take-home salary
     */
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
    
    // =====================================================
    // GETTERS AND SETTERS
    // =====================================================
    // JPA uses getters and setters to access and modify entity data
    // These methods allow the framework to read/write data to/from the database
    
    /**
     * Get the tax calculation ID (primary key)
     * @return Tax calculation ID
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Set the tax calculation ID
     * Usually not called directly - JPA manages this automatically
     * @param id Tax calculation ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Get the employee ID
     * @return Employee ID
     */
    public Long getEmployeeId() {
        return employeeId;
    }
    
    /**
     * Set the employee ID
     * @param employeeId Employee ID
     */
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    
    /**
     * Get the annual cost to company
     * @return Annual CTC
     */
    public Double getAnnualCtc() {
        return annualCtc;
    }
    
    /**
     * Set the annual cost to company
     * @param annualCtc Annual CTC
     */
    public void setAnnualCtc(Double annualCtc) {
        this.annualCtc = annualCtc;
    }
    
    /**
     * Get the total deductions
     * @return Total deductions
     */
    public Double getDeductions() {
        return deductions;
    }
    
    /**
     * Set the total deductions
     * @param deductions Total deductions
     */
    public void setDeductions(Double deductions) {
        this.deductions = deductions;
    }
    
    /**
     * Get the taxable income
     * @return Taxable income
     */
    public Double getTaxableIncome() {
        return taxableIncome;
    }
    
    /**
     * Set the taxable income
     * @param taxableIncome Taxable income
     */
    public void setTaxableIncome(Double taxableIncome) {
        this.taxableIncome = taxableIncome;
    }
    
    /**
     * Get the surcharge amount
     * @return Surcharge
     */
    public Double getSurcharge() {
        return surcharge;
    }
    
    /**
     * Set the surcharge amount
     * @param surcharge Surcharge
     */
    public void setSurcharge(Double surcharge) {
        this.surcharge = surcharge;
    }
    
    /**
     * Get the cess amount
     * @return Cess
     */
    public Double getCess() {
        return cess;
    }
    
    /**
     * Set the cess amount
     * @param cess Cess
     */
    public void setCess(Double cess) {
        this.cess = cess;
    }
    
    /**
     * Get the net payable tax
     * @return Net payable tax
     */
    public Double getNetPayableTax() {
        return netPayableTax;
    }
    
    /**
     * Set the net payable tax
     * @param netPayableTax Net payable tax
     */
    public void setNetPayableTax(Double netPayableTax) {
        this.netPayableTax = netPayableTax;
    }
    
    /**
     * Get the take-home salary
     * @return Take-home salary
     */
    public Double getTakeHomeSalary() {
        return takeHomeSalary;
    }
    
    /**
     * Set the take-home salary
     * @param takeHomeSalary Take-home salary
     */
    public void setTakeHomeSalary(Double takeHomeSalary) {
        this.takeHomeSalary = takeHomeSalary;
    }
} 