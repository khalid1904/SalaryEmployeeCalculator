// =====================================================
// EMPLOYEE REPOSITORY - DATA ACCESS LAYER
// =====================================================
// This interface handles all database operations for Employee entities
// Spring Data JPA automatically provides common database operations
// No need to write SQL queries - Spring generates them automatically

package com.example.salarycalculator.repository;

// Import our custom entity class
import com.example.salarycalculator.entity.Employee;

// Spring Data JPA imports
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Employee Repository - Data access layer for Employee entities
 * 
 * This interface extends JpaRepository, which automatically provides:
 * - Basic CRUD operations (Create, Read, Update, Delete)
 * - Pagination and sorting
 * - Query methods based on method names
 * 
 * Spring Data JPA automatically creates implementations of this interface
 * You don't need to write any implementation code!
 * 
 * Available Methods (automatically provided by JpaRepository):
 * - save(Employee) - Save or update an employee
 * - findById(Long) - Find employee by ID
 * - findAll() - Get all employees
 * - deleteById(Long) - Delete employee by ID
 * - count() - Count total employees
 * - existsById(Long) - Check if employee exists
 * 
 * Custom Query Methods (you can add these):
 * - findByAge(Integer age) - Find employees by age
 * - findByNameContaining(String name) - Find employees with name containing text
 * - findByMonthlyCtcGreaterThan(Double ctc) - Find employees with CTC > value
 */
@Repository  // Tells Spring this is a repository component
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // =====================================================
    // AUTOMATICALLY PROVIDED METHODS
    // =====================================================
    // The following methods are automatically provided by JpaRepository:
    //
    // SAVE OPERATIONS
    // Employee save(Employee employee) - Save or update employee
    // List<Employee> saveAll(List<Employee> employees) - Save multiple employees
    //
    // FIND OPERATIONS
    // Optional<Employee> findById(Long id) - Find by ID
    // List<Employee> findAll() - Get all employees
    // List<Employee> findAllById(List<Long> ids) - Find by multiple IDs
    // boolean existsById(Long id) - Check if exists
    // long count() - Count total records
    //
    // DELETE OPERATIONS
    // void deleteById(Long id) - Delete by ID
    // void delete(Employee employee) - Delete employee object
    // void deleteAll() - Delete all employees
    // void deleteAllById(List<Long> ids) - Delete by multiple IDs
    //
    // CUSTOM QUERY METHODS (you can add these)
    // List<Employee> findByName(String name) - Find by exact name
    // List<Employee> findByNameContaining(String name) - Find by name containing text
    // List<Employee> findByAge(Integer age) - Find by age
    // List<Employee> findByMonthlyCtcGreaterThan(Double ctc) - Find by CTC > value
    // List<Employee> findByAgeBetween(Integer minAge, Integer maxAge) - Find by age range
    // Employee findFirstByNameOrderByIdDesc(String name) - Find first by name, ordered by ID desc
    
    // =====================================================
    // CUSTOM QUERY METHODS (EXAMPLES)
    // =====================================================
    // Uncomment and modify these methods as needed:
    
    /**
     * Find employees by name (exact match)
     * Spring automatically generates: SELECT * FROM employees WHERE name = ?
     */
    // List<Employee> findByName(String name);
    
    /**
     * Find employees by name containing text (partial match)
     * Spring automatically generates: SELECT * FROM employees WHERE name LIKE %?%
     */
    // List<Employee> findByNameContaining(String name);
    
    /**
     * Find employees by age
     * Spring automatically generates: SELECT * FROM employees WHERE age = ?
     */
    // List<Employee> findByAge(Integer age);
    
    /**
     * Find employees with CTC greater than specified value
     * Spring automatically generates: SELECT * FROM employees WHERE monthly_ctc > ?
     */
    // List<Employee> findByMonthlyCtcGreaterThan(Double ctc);
    
    /**
     * Find employees by age range
     * Spring automatically generates: SELECT * FROM employees WHERE age BETWEEN ? AND ?
     */
    // List<Employee> findByAgeBetween(Integer minAge, Integer maxAge);
    
    /**
     * Find first employee by name, ordered by ID descending
     * Spring automatically generates: SELECT * FROM employees WHERE name = ? ORDER BY id DESC LIMIT 1
     */
    // Employee findFirstByNameOrderByIdDesc(String name);
} 