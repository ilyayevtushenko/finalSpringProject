package com.example.finalSpringProject.model.service;

import com.example.finalSpringProject.model.domain.Payment;

import java.math.BigDecimal;
import java.util.Set;

public interface PaymentService {
    void createPayment (String sender, String receiver, BigDecimal sum);
    Set<Payment> findAllByEmail (String email);
}
