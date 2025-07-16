// =====================================================
// EMPLOYEE ENTITY - JPA DATABASE MAPPING
// =====================================================
// This class represents an Employee in the database
// JPA (Java Persistence API) automatically maps this class to a database table
// Each field in this class becomes a column in the database table

package com.example.salarycalculator.entity;

// JPA (Java Persistence API) imports
// These annotations tell JPA how to map this class to the database
import javax.persistence.*;

/**
 * Employee Entity - Represents an employee in the database
 * 
 * This class is annotated with JPA annotations that tell the framework:
 * - How to create the database table
 * - How to map Java objects to database rows
 * - How to handle relationships between tables
 * 
 * JPA Entity Lifecycle:
 * 1. New - Object created but not yet persisted
 * 2. Managed - Object is being tracked by JPA
 * 3. Detached - Object is no longer tracked
 * 4. Removed - Object is marked for deletion
 */
@Entity  // Tells JPA this class should be mapped to a database table
@Table(name = "employees")  // Specifies the database table name
public class Employee {
    
    /**
     * Primary Key - Unique identifier for each employee
     * @Id - Marks this field as the primary key
     * @GeneratedValue - Tells JPA to automatically generate values
     * GenerationType.IDENTITY - Uses database auto-increment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Employee Name - Required field
     * @Column - Maps this field to a database column
     * nullable = false - Makes this field required (NOT NULL in database)
     */
    @Column(name = "name", nullable = false)
    private String name;
    
    /**
     * Employee Age - Optional field
     * @Column - Maps this field to a database column
     * No nullable = false means this field is optional
     */
    @Column(name = "age")
    private Integer age;
    
    /**
     * Monthly Cost to Company (CTC) - Required field
     * This represents the total monthly salary package
     * @Column - Maps this field to a database column
     * nullable = false - Makes this field required
     */
    @Column(name = "monthly_ctc", nullable = false)
    private Double monthlyCtc;
    
    // =====================================================
    // CONSTRUCTORS
    // =====================================================
    
    /**
     * Default constructor - Required by JPA
     * JPA needs a no-argument constructor to create entity instances
     */
    public Employee() {}
    
    /**
     * Constructor with all fields
     * Useful for creating Employee objects with initial data
     * 
     * @param name - Employee name
     * @param age - Employee age
     * @param monthlyCtc - Monthly cost to company
     */
    public Employee(String name, Integer age, Double monthlyCtc) {
        this.name = name;
        this.age = age;
        this.monthlyCtc = monthlyCtc;
    }
    
    // =====================================================
    // GETTERS AND SETTERS
    // =====================================================
    // JPA uses getters and setters to access and modify entity data
    // These methods allow the framework to read/write data to/from the database
    
    /**
     * Get the employee ID (primary key)
     * @return Employee ID
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Set the employee ID
     * Usually not called directly - JPA manages this automatically
     * @param id Employee ID
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Get the employee name
     * @return Employee name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Set the employee name
     * @param name Employee name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Get the employee age
     * @return Employee age
     */
    public Integer getAge() {
        return age;
    }
    
    /**
     * Set the employee age
     * @param age Employee age
     */
    public void setAge(Integer age) {
        this.age = age;
    }
    
    /**
     * Get the monthly cost to company (CTC)
     * @return Monthly CTC
     */
    public Double getMonthlyCtc() {
        return monthlyCtc;
    }
    
    /**
     * Set the monthly cost to company (CTC)
     * @param monthlyCtc Monthly CTC
     */
    public void setMonthlyCtc(Double monthlyCtc) {
        this.monthlyCtc = monthlyCtc;
    }
} 