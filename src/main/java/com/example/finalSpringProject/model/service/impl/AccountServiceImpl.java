package com.example.finalSpringProject.model.service.impl;

import com.example.finalSpringProject.model.domain.Account;
import com.example.finalSpringProject.model.domain.CreditCard;
import com.example.finalSpringProject.model.domain.User;
import com.example.finalSpringProject.model.entity.AccountEntity;
import com.example.finalSpringProject.model.entity.CreditCardEntity;
import com.example.finalSpringProject.model.entity.UserEntity;
import com.example.finalSpringProject.model.exeptions.EntityNotFoundRuntimeException;
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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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


}
