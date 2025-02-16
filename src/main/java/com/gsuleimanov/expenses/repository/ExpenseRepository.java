package com.gsuleimanov.expenses.repository;

import com.gsuleimanov.expenses.model.ExpenseEntity;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

    Page<ExpenseEntity> findByExpenseDateBetween(LocalDate startDate, LocalDate endDate, PageRequest pageRequest);
}