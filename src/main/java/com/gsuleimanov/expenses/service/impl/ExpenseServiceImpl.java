package com.gsuleimanov.expenses.service.impl;

import com.gsuleimanov.expenses.converter.ExpenseConverter;
import com.gsuleimanov.expenses.entity.ExpenseDao;
import com.gsuleimanov.expenses.exception.ResourceNotFoundException;
import com.gsuleimanov.expenses.model.Expense;
import com.gsuleimanov.expenses.model.ExpenseRequest;
import com.gsuleimanov.expenses.model.PagedExpenses;
import com.gsuleimanov.expenses.repository.ExpenseRepository;
import com.gsuleimanov.expenses.repository.CategoryRepository;
import com.gsuleimanov.expenses.repository.PaymentMethodRepository;
import com.gsuleimanov.expenses.repository.UserRepository;
import com.gsuleimanov.expenses.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ExpenseConverter expenseConverter;
    private final CategoryRepository categoryRepository;
    private final PaymentMethodRepository paymentMethodRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public PagedExpenses findExpenses(LocalDate startDate, LocalDate endDate, String category, Integer page, Integer size) {
        var pageRequest = PageRequest.of(page, size);
        Page<ExpenseDao> expenses;

        if (startDate != null && endDate != null) {
            expenses = expenseRepository.findByExpenseDateBetween(startDate, endDate, pageRequest);
        } else {
            expenses = expenseRepository.findAll(pageRequest);
        }

        var pagedExpenses = new PagedExpenses();
        pagedExpenses.setContent(expenses.map(expense ->
            expenseConverter.toDto(expense, expense.getCategory(), expense.getPaymentMethod())
        ).getContent());
        pagedExpenses.setTotalElements(expenses.getTotalElements());
        pagedExpenses.setTotalPages(expenses.getTotalPages());
        pagedExpenses.setSize(expenses.getSize());
        pagedExpenses.setNumber(expenses.getNumber());

        return pagedExpenses;
    }

    @Override
    @Transactional(readOnly = true)
    public Expense findExpenseById(Long id) {
        var expense = expenseRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Expense not found: " + id));
        return expenseConverter.toDto(expense, expense.getCategory(), expense.getPaymentMethod());
    }

    @Override
    @Transactional
    public Expense createExpense(ExpenseRequest request) {
        // TODO: Replace with actual authenticated user when security is implemented
        var user = userRepository.findById(1L)
            .orElseThrow(() -> new RuntimeException("Default user not found"));

        var category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + request.getCategoryId()));

        var paymentMethod = paymentMethodRepository.findById(request.getPaymentMethodId())
            .orElseThrow(() -> new ResourceNotFoundException("Payment method not found: " + request.getPaymentMethodId()));

        var expense = ExpenseDao.builder()
            .user(user)
            .category(category)
            .paymentMethod(paymentMethod)
            .amount(request.getAmount())
            .description(request.getDescription())
            .location(request.getLocation())
            .expenseDate(request.getExpenseDate())
            .build();

        var savedExpense = expenseRepository.save(expense);
        return expenseConverter.toDto(savedExpense, category, paymentMethod);
    }

    @Override
    @Transactional
    public Expense updateExpense(Long id, ExpenseRequest request) {
        var existingExpense = expenseRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Expense not found: " + id));

        var category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + request.getCategoryId()));

        var paymentMethod = paymentMethodRepository.findById(request.getPaymentMethodId())
            .orElseThrow(() -> new ResourceNotFoundException("Payment method not found: " + request.getPaymentMethodId()));

        var updatedExpense = ExpenseDao.builder()
            .id(existingExpense.getId())
            .user(existingExpense.getUser())
            .category(category)
            .paymentMethod(paymentMethod)
            .amount(request.getAmount())
            .description(request.getDescription())
            .location(request.getLocation())
            .expenseDate(request.getExpenseDate())
            .build();

        var savedExpense = expenseRepository.save(updatedExpense);
        return expenseConverter.toDto(savedExpense, category, paymentMethod);
    }

    @Override
    @Transactional
    public void deleteExpense(Long id) {
        if (!expenseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Expense not found: " + id);
        }
        expenseRepository.deleteById(id);
    }
}