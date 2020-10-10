package com.example.finalSpringProject.model.service;

import com.example.finalSpringProject.model.domain.Account;
import com.example.finalSpringProject.model.domain.CreditCard;

import java.util.Set;

public interface AccountService {
    Set<Account> findAllByName (String name);
}
