package com.gsuleimanov.expenses.service.impl;

import com.gsuleimanov.expenses.converter.PaymentMethodConverter;
import com.gsuleimanov.expenses.model.PaymentMethodDao;
import com.gsuleimanov.expenses.model.PaymentMethod;
import com.gsuleimanov.expenses.model.PaymentMethodRequest;
import com.gsuleimanov.expenses.repository.PaymentMethodRepository;
import com.gsuleimanov.expenses.repository.UserRepository;
import com.gsuleimanov.expenses.service.PaymentMethodService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodConverter paymentMethodConverter;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PaymentMethod> findAllPaymentMethods() {
        // TODO: Implement user-specific payment methods when authentication is added
        List<PaymentMethodDao> paymentMethods = paymentMethodRepository.findAll();
        return paymentMethods.stream()
                .map(paymentMethodConverter::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PaymentMethod createPaymentMethod(PaymentMethodRequest paymentMethodRequest) {
        // TODO: Replace with actual authenticated user when security is implemented
        var user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Default user not found"));

        var paymentMethod = PaymentMethodDao.builder()
                .name(paymentMethodRequest.getName())
                .user(user)
                .build();

        var savedPaymentMethod = paymentMethodRepository.save(paymentMethod);
        return paymentMethodConverter.toDto(savedPaymentMethod);
    }
}