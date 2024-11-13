package com.gsuleimanov.expenses.repository;

import com.gsuleimanov.expenses.entity.ExpenseDao;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseDao, Long> {

    Page<ExpenseDao> findByExpenseDateBetween(LocalDate startDate, LocalDate endDate, PageRequest pageRequest);
}