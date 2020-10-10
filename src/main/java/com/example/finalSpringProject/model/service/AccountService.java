package com.example.finalSpringProject.model.service;

import com.example.finalSpringProject.model.domain.Account;

import java.math.BigDecimal;
import java.util.Set;

public interface AccountService {
    Set<Account> findAllByName (String name);

    void addBalance (String number, BigDecimal balance);
    void removeBalance (String number, BigDecimal balance);
}
