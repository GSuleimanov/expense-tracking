package com.gsuleimanov.expenses.service;

import com.gsuleimanov.expenses.model.PaymentMethod;
import com.gsuleimanov.expenses.model.PaymentMethodRequest;

import java.util.List;

public interface PaymentMethodService {
    List<PaymentMethod> findAllPaymentMethods();
    PaymentMethod createPaymentMethod(PaymentMethodRequest paymentMethodRequest);
} 