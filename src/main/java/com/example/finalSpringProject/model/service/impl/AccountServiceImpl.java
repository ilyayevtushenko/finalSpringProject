package com.example.finalSpringProject.model.service.impl;

import com.example.finalSpringProject.model.domain.Account;
import com.example.finalSpringProject.model.domain.User;
import com.example.finalSpringProject.model.entity.AccountEntity;
import com.example.finalSpringProject.model.entity.CreditCardEntity;
import com.example.finalSpringProject.model.entity.UserEntity;
import com.example.finalSpringProject.model.exeptions.InvalidDataRuntimeException;
import com.example.finalSpringProject.model.repository.AccountRepository;
import com.example.finalSpringProject.model.repository.CreditCardRepository;
import com.example.finalSpringProject.model.service.AccountService;
import com.example.finalSpringProject.model.service.UserService;
import com.example.finalSpringProject.model.service.mapper.AccountMapper;
import com.example.finalSpringProject.model.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountServiceImpl implements AccountService {
private final UserService userService;
private final UserMapper userMapper;
private final CreditCardRepository creditCardRepository;
private final AccountMapper accountMapper;
private final AccountRepository accountRepository;
    @Override
    public Set<Account> findAllByName(String name) {
        User user = userService.findByEmail(name);
        UserEntity userEntity = userMapper.userToUserEntity(user);

        Set<CreditCardEntity> creditCardEntities = new HashSet<>(creditCardRepository
                .findAllByUsers(userEntity));


        Set<AccountEntity> accountEntities = creditCardEntities.stream().map(CreditCardEntity::getAccounts).collect(Collectors.toSet());
       return accountEntities.stream().map(accountMapper::accountEntityToAccount).collect(Collectors.toSet());
    }

    @Override
    public Account findByCardNumber (String number) {
        CreditCardEntity creditCardEntity = creditCardRepository.findByNumber(number)
                .orElseThrow(() -> new InvalidDataRuntimeException("Cannot find credit card by this number"));
        AccountEntity accountEntity = creditCardEntity.getAccounts();
        return accountMapper.accountEntityToAccount(accountEntity);
    }


    @Override
    public void addBalance(String number, BigDecimal balance) {

        CreditCardEntity creditCardEntity = creditCardRepository.findByNumber(number)
                .orElseThrow(() -> new InvalidDataRuntimeException("Invalid input Credit Card data"));

            AccountEntity accountEntity = creditCardEntity.getAccounts();
            BigDecimal newBalance = accountEntity.getBalance();
            newBalance = newBalance.add(balance);
            accountEntity.setBalance(newBalance);
            accountRepository.save(accountEntity);
    }

    @Override
    public void removeBalance(String number, BigDecimal balance) {
        CreditCardEntity creditCardEntity = creditCardRepository.findByNumber(number)
                .orElseThrow(() -> new InvalidDataRuntimeException("Invalid input Credit Card data"));

        AccountEntity accountEntity = creditCardEntity.getAccounts();
        BigDecimal newBalance = accountEntity.getBalance();
        newBalance = newBalance.subtract(balance);
        accountEntity.setBalance(newBalance);
        accountRepository.save(accountEntity);

    }

    public Page<AccountEntity> resolvePagesAccountUser(int currentPage, int pageSize, String sortBy, String searchBy) {

        PageRequest sorted = PageRequest.of(currentPage - 1, pageSize, Sort.by(sortBy));
        if (searchBy.equals("")) {
            return accountRepository.findAllById(0L, sorted);
        } else {
            return accountRepository.findAllByNameLike(0, searchBy, sorted);
        }
    }

    public Optional<List<Integer>> findPageNumbers(int quantity){

            List<Integer> pages = null;
            if (quantity > 1) {
                pages = IntStream
                        .rangeClosed(1, quantity)
                        .boxed()
                        .collect(Collectors.toList());
            }

            return Optional.ofNullable(pages);

        }


    }

