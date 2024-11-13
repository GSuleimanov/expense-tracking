package com.gsuleimanov.expenses.service;

import com.gsuleimanov.expenses.model.Expense;
import com.gsuleimanov.expenses.model.ExpenseRequest;
import com.gsuleimanov.expenses.model.PagedExpenses;
import java.time.LocalDate;

public interface ExpenseService {
    PagedExpenses findExpenses(LocalDate startDate, LocalDate endDate, String category, Integer page, Integer size);
    Expense findExpenseById(Long id);
    Expense createExpense(ExpenseRequest request);
    Expense updateExpense(Long id, ExpenseRequest request);
    void deleteExpense(Long id);
} 