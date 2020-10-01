package com.example.finalSpringProject.model.service.mapper;

import com.example.finalSpringProject.model.domain.CreditCard;
import com.example.finalSpringProject.model.entity.CreditCardEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CreditCardMapper {

    private final UserMapper userMapper;
    private final AccountMapper accountMapper;
    public CreditCardEntity creditCardToCreditCardEntity (CreditCard creditCard) {
        return Objects.isNull(creditCard) ?
                null :
                CreditCardEntity.builder()
                        .id(creditCard.getId())
                        .number(creditCard.getNumber())
                        .CVV2(creditCard.getCVV2())
                        .user(userMapper.userToUserEntity(creditCard.getUser()))
                        .account(accountMapper.accountToAccountEntity(creditCard.getAccount()))
                        .build();
    }

    public CreditCard creditCardEntityToCreditCard (CreditCardEntity creditCardEntity) {
        return Objects.isNull(creditCardEntity) ?
                null :
                CreditCard.builder()
                        .id(creditCardEntity.getId())
                        .number(creditCardEntity.getNumber())
                        .CVV2(creditCardEntity.getCVV2())
                        .user(userMapper.userEntityToUser(creditCardEntity.getUser()))
                        .account(accountMapper.accountEntityToAccount(creditCardEntity.getAccount()))
                        .build();
    }

    public List<CreditCardEntity> mapListToEntity (List<CreditCard> creditCards) {
        return creditCards.stream()
                .map(this::creditCardToCreditCardEntity)
                .collect(Collectors.toList());
    }

    public List<CreditCard> mapEntityToList (List<CreditCardEntity> creditCardEntities) {
        return creditCardEntities.stream()
                .map(this::creditCardEntityToCreditCard)
                .collect(Collectors.toList());
    }


}
