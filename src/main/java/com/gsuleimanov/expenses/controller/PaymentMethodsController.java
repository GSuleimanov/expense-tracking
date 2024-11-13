package com.gsuleimanov.expenses.controller;

import com.gsuleimanov.expenses.api.PaymentMethodsApi;
import com.gsuleimanov.expenses.model.PaymentMethod;
import com.gsuleimanov.expenses.model.PaymentMethodRequest;
import com.gsuleimanov.expenses.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentMethodsController implements PaymentMethodsApi {
    private final PaymentMethodService paymentMethodService;

    @Override
    public ResponseEntity<List<PaymentMethod>> listPaymentMethods() {
        var paymentMethods = paymentMethodService.findAllPaymentMethods();
        return ResponseEntity.ok(paymentMethods);
    }

    @Override
    public ResponseEntity<PaymentMethod> createPaymentMethod(PaymentMethodRequest paymentMethodRequest) {
        var createdPaymentMethod = paymentMethodService.createPaymentMethod(paymentMethodRequest);
        return ResponseEntity.status(201).body(createdPaymentMethod);
    }
}