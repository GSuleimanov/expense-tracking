package com.gsuleimanov.expenses.controller;

import com.gsuleimanov.expenses.api.ExpensesApi;
import com.gsuleimanov.expenses.model.Expense;
import com.gsuleimanov.expenses.model.ExpenseRequest;
import com.gsuleimanov.expenses.model.PagedExpenses;
import com.gsuleimanov.expenses.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class ExpensesController implements ExpensesApi {
    private final ExpenseService expenseService;

    @Override
    public ResponseEntity<PagedExpenses> listExpenses(
        LocalDate startDate,
        LocalDate endDate,
        String category,
        Integer page,
        Integer size
    ) {
        var expenses = expenseService.findExpenses(startDate, endDate, category, page, size);
        return ResponseEntity.ok(expenses);
    }

    @Override
    public ResponseEntity<Void> deleteExpense(Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Expense> getExpenseById(Long id) {
        var expense = expenseService.findExpenseById(id);
        return ResponseEntity.ok(expense);
    }

    @Override
    public ResponseEntity<Expense> updateExpense(Long id, ExpenseRequest expenseRequest) {
        var updatedExpense = expenseService.updateExpense(id, expenseRequest);
        return ResponseEntity.ok(updatedExpense);
    }

    @Override
    public ResponseEntity<Expense> createExpense(ExpenseRequest expenseRequest) {
        var createdExpense = expenseService.createExpense(expenseRequest);
        return ResponseEntity.status(201).body(createdExpense);
    }
}