package com.example.finalSpringProject.model.service;

import com.example.finalSpringProject.model.domain.CreditCard;
import com.example.finalSpringProject.model.domain.User;

import java.util.Set;

public interface CreditCardService {

    void addCreditCard (CreditCard creditCard, String name);
    Set<CreditCard> getAllCardsByUserName (String name);
}
