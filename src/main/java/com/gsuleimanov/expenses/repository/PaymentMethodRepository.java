package com.gsuleimanov.expenses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gsuleimanov.expenses.entity.PaymentMethodDao;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethodDao, Long> {
}
