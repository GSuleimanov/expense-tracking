package com.gsuleimanov.expenses.converter;

import com.gsuleimanov.expenses.model.CategoryEntity;
import com.gsuleimanov.expenses.model.ExpenseEntity;
import com.gsuleimanov.expenses.model.PaymentMethodEntity;
import com.gsuleimanov.expenses.model.Expense;
import com.gsuleimanov.expenses.model.ExpenseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpenseConverter {
    private final PaymentMethodConverter paymentMethodConverter;
    private final CategoryConverter categoryConverter;

    public Expense toDto(ExpenseEntity expense) {
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

    public ExpenseEntity toRecord(ExpenseRequest request) {
        if (request == null) return null;

        return ExpenseEntity.builder()
                .amount(request.getAmount())
                .description(request.getDescription())
                .expenseDate(request.getExpenseDate())
                .location(request.getLocation())
                .category(CategoryEntity.reference(request.getCategoryId()))
                .paymentMethod(PaymentMethodEntity.reference(request.getPaymentMethodId()))
                .build();
    }
}