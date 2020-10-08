package com.example.finalSpringProject.model.service.impl;

import com.example.finalSpringProject.model.domain.Account;
import com.example.finalSpringProject.model.domain.CreditCard;
import com.example.finalSpringProject.model.domain.User;
import com.example.finalSpringProject.model.entity.AccountEntity;
import com.example.finalSpringProject.model.entity.CreditCardEntity;
import com.example.finalSpringProject.model.exeptions.InvalidDataRuntimeException;
import com.example.finalSpringProject.model.repository.AccountRepository;
import com.example.finalSpringProject.model.repository.CreditCardRepository;
import com.example.finalSpringProject.model.repository.UserRepository;
import com.example.finalSpringProject.model.service.CreditCardService;
import com.example.finalSpringProject.model.service.UserService;
import com.example.finalSpringProject.model.service.mapper.AccountMapper;
import com.example.finalSpringProject.model.service.mapper.CreditCardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardServiceImpl implements CreditCardService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CreditCardRepository creditCardRepository;
    private final CreditCardMapper creditCardMapper;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public void addCreditCard(CreditCard creditCard, String name) {
        if (Objects.isNull(creditCard)) {
            log.warn("Invalid input bus data");
            throw new InvalidDataRuntimeException("Invalid input Credit Card data");
        }
        Optional<CreditCardEntity> busEntity = creditCardRepository.findByNumber(creditCard.getNumber());
        if (busEntity.isPresent()) {
            log.warn("Credit Card with this number already exists");
            throw new InvalidDataRuntimeException("Credit Card with this number already exists");
        }
        Account account = new Account();
        account.setName();
        account.setBalance(BigDecimal.valueOf(0.0));
        AccountEntity accountEntity = accountMapper.accountToAccountEntity(account);
        accountRepository.save(accountEntity);

        User user = userService.findByEmail(name);

        creditCard.setUser(user);
        creditCard.setAccount(account);
        CreditCardEntity entityCard = creditCardMapper.creditCardToCreditCardEntity(creditCard);
        creditCardRepository.save(entityCard);
    }

}
