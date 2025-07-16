package com.example.salarycalculator.repository;

import com.example.salarycalculator.entity.TaxCalculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxCalculationRepository extends JpaRepository<TaxCalculation, Long> {
    // Find tax calculation by employee ID
    TaxCalculation findByEmployeeId(Long employeeId);
} 