package com.example.finalSpringProject.model.service.mapper;

import com.example.finalSpringProject.model.domain.Account;
import com.example.finalSpringProject.model.entity.AccountEntity;
import com.example.finalSpringProject.model.entity.CreditCardEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AccountMapper {

    private final ModelMapper modelMapper;

    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public Account accountEntityToAccount (AccountEntity accountEntity){
        return Objects.isNull(accountEntity) ? null
                : modelMapper.map(accountEntity, Account.class);
    }

    public AccountEntity accountToAccountEntity (Account account) {
        return Objects.isNull(account) ? null
                : modelMapper.map(account, AccountEntity.class);


    }
}
