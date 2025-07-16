package com.example.salarycalculator.entity;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "age")
    private Integer age;
    
    @Column(name = "monthly_ctc", nullable = false)
    private Double monthlyCtc;
    
    // Default constructor
    public Employee() {}
    
    // Constructor with fields
    public Employee(String name, Integer age, Double monthlyCtc) {
        this.name = name;
        this.age = age;
        this.monthlyCtc = monthlyCtc;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getAge() {
        return age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    public Double getMonthlyCtc() {
        return monthlyCtc;
    }
    
    public void setMonthlyCtc(Double monthlyCtc) {
        this.monthlyCtc = monthlyCtc;
    }
} 