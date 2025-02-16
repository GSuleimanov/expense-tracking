package com.gsuleimanov.expenses.converter;

import com.gsuleimanov.expenses.model.PaymentMethodEntity;
import com.gsuleimanov.expenses.model.PaymentMethod;
import com.gsuleimanov.expenses.model.PaymentMethodRequest;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodConverter {

    public PaymentMethod toDto(PaymentMethodEntity record) {
        return record == null
                ? null
                : new PaymentMethod()
                        .id(record.getId())
                        .name(record.getName())
                        .createdAt(record.getCreatedAt());
    }

    public PaymentMethodEntity toRecord(PaymentMethodRequest request) {
        if (request == null) return null;

        return PaymentMethodEntity.builder()
                .name(request.getName())
                .build();
    }
}