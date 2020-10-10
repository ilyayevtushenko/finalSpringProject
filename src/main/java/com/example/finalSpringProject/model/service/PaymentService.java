package com.example.finalSpringProject.model.service;

import java.math.BigDecimal;

public interface PaymentService {
    void createPayment (String sender, String receiver, BigDecimal sum);
}
