package com.gsuleimanov.expenses.repository;

import com.gsuleimanov.expenses.entity.ExpenseDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseDao, Long> {
}