package com.gsuleimanov.expenses.converter;

import com.gsuleimanov.expenses.model.CategoryDao;
import com.gsuleimanov.expenses.model.ExpenseDao;
import com.gsuleimanov.expenses.model.PaymentMethodDao;
import com.gsuleimanov.expenses.model.Expense;
import com.gsuleimanov.expenses.model.ExpenseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpenseConverter {
    private final PaymentMethodConverter paymentMethodConverter;
    private final CategoryConverter categoryConverter;

    public Expense toDto(ExpenseDao expense) {
        if (expense == null) return null;

        return new Expense()
                .id(expense.getId())
                .amount(expense.getAmount())
                .description(expense.getDescription())
                .expenseDate(expense.getExpenseDate())
                .location(expense.getLocation())
                .category(categoryConverter.toDto(expense.getCategory()))
                .paymentMethod(paymentMethodConverter.toDto(expense.getPaymentMethod()))
                .createdAt(expense.getCreatedAt())
                .updatedAt(expense.getUpdatedAt());
    }

    public ExpenseDao toRecord(ExpenseRequest request) {
        if (request == null) return null;

        return ExpenseDao.builder()
                .amount(request.getAmount())
                .description(request.getDescription())
                .expenseDate(request.getExpenseDate())
                .location(request.getLocation())
                .category(CategoryDao.reference(request.getCategoryId()))
                .paymentMethod(PaymentMethodDao.reference(request.getPaymentMethodId()))
                .build();
    }
}