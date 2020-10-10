package com.example.finalSpringProject.model.service;

import com.example.finalSpringProject.model.domain.CreditCard;

import java.util.Set;

public interface CreditCardService {

    void addCreditCard (CreditCard creditCard, String name);
    Set<CreditCard> getAllCardsByUserName (String name);
}
