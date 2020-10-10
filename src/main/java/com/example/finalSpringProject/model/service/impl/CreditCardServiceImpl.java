package com.example.finalSpringProject.model.service.impl;

import com.example.finalSpringProject.model.domain.Account;
import com.example.finalSpringProject.model.domain.CreditCard;
import com.example.finalSpringProject.model.domain.User;
import com.example.finalSpringProject.model.entity.AccountEntity;
import com.example.finalSpringProject.model.entity.CreditCardEntity;
import com.example.finalSpringProject.model.entity.UserEntity;
import com.example.finalSpringProject.model.exeptions.InvalidDataRuntimeException;
import com.example.finalSpringProject.model.repository.AccountRepository;
import com.example.finalSpringProject.model.repository.CreditCardRepository;
import com.example.finalSpringProject.model.repository.UserRepository;
import com.example.finalSpringProject.model.service.CreditCardService;
import com.example.finalSpringProject.model.service.UserService;
import com.example.finalSpringProject.model.service.mapper.AccountMapper;
import com.example.finalSpringProject.model.service.mapper.CreditCardMapper;
import com.example.finalSpringProject.model.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final UserMapper userMapper;

    @Override
    public void addCreditCard(CreditCard creditCard, String name) {
        if (Objects.isNull(creditCard)) {
            log.warn("Invalid input bus data");
            throw new InvalidDataRuntimeException("Invalid input Credit Card data");
        }
        Optional<CreditCardEntity> creditCardEntity = creditCardRepository.findByNumber(creditCard.getNumber());
        if (creditCardEntity.isPresent()) {
            log.warn("Credit Card with this number already exists");
            throw new InvalidDataRuntimeException("Credit Card with this number already exists");
        }
        Account account = new Account();
        account.setName();
        account.setBalance(BigDecimal.valueOf(0.0));
        AccountEntity accountEntity = accountMapper.accountToAccountEntity(account);
        accountRepository.save(accountEntity);

        User user = userService.findByEmail(name);
//
        CreditCardEntity entityCard = creditCardMapper.creditCardToCreditCardEntity(creditCard);
        entityCard.setAccounts(accountMapper.accountToAccountEntity(account));
        entityCard.setUsers(userMapper.userToUserEntity(user));
        creditCardRepository.save(entityCard);
    }

    @Override
    public Set<CreditCard> getAllCardsByUserName(String name) {
        User user = userService.findByEmail(name);
        UserEntity userEntity = userMapper.userToUserEntity(user);

        Set<CreditCardEntity> creditCardEntities = new HashSet<>(creditCardRepository
                .findAllByUsers(userEntity));

        return creditCardEntities.stream().map(creditCardMapper::creditCardEntityToCreditCard)
                .collect(Collectors.toSet());

    }


}
