package com.example.finalSpringProject.model.service;

import com.example.finalSpringProject.model.domain.Account;
import com.example.finalSpringProject.model.entity.AccountEntity;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AccountService {
    Set<Account> findAllByName (String name);
    Account findByCardNumber (String number);
    void addBalance (String number, BigDecimal balance);
    void removeBalance (String number, BigDecimal balance);
    Page<AccountEntity> resolvePagesAccountUser(int currentPage, int pageSize, String sortBy, String searchBy);
    public Optional<List<Integer>> findPageNumbers(int quantity);
}
