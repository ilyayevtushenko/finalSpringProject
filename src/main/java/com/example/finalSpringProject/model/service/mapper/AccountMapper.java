package com.example.finalSpringProject.model.service.mapper;

import com.example.finalSpringProject.model.domain.Account;
import com.example.finalSpringProject.model.entity.AccountEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AccountMapper {

    private final PaymentMapper paymentMapper;

    public Account accountEntityToAccount (AccountEntity accountEntity){
        return Objects.isNull(accountEntity) ?
                null :
                Account.builder()
                .id(accountEntity.getId())
                .name(accountEntity.getName())
                .balance(accountEntity.getBalance())
                .payment(paymentMapper.mapEntityToList(accountEntity.getPayment()))
                .build();
    }

    public AccountEntity accountToAccountEntity (Account account) {
        return Objects.isNull(account) ?
                null :
                AccountEntity.builder()
                        .id(account.getId())
                        .name(account.getName())
                        .balance(account.getBalance())
                        .payment(paymentMapper.mapListToEntity(account.getPayment()))
                        .build();

    }
}
