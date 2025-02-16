package com.gsuleimanov.expenses.repository;

import com.gsuleimanov.expenses.model.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity, Long> {
    // Add any specific query methods if needed
}
