package com.example.finalSpringProject.model.service.mapper;

import com.example.finalSpringProject.model.domain.CreditCard;
import com.example.finalSpringProject.model.entity.CreditCardEntity;
import com.example.finalSpringProject.model.entity.PaymentEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CreditCardMapper {

    private final ModelMapper modelMapper;

    public CreditCardMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public CreditCardEntity creditCardToCreditCardEntity (CreditCard creditCard) {
        return Objects.isNull(creditCard) ? null
                : modelMapper.map(creditCard, CreditCardEntity.class);
    }

    public CreditCard creditCardEntityToCreditCard (CreditCardEntity creditCardEntity) {
        return Objects.isNull(creditCardEntity) ? null
                : modelMapper.map(creditCardEntity, CreditCard.class);
    }



}
