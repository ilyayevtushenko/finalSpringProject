package com.example.finalSpringProject.model.service.mapper;

import com.example.finalSpringProject.model.domain.User;
import com.example.finalSpringProject.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserMapper {

    private final CreditCardMapper creditCardMapper;
    private final PaymentMapper paymentMapper;

    public UserEntity userToUserEntity (User user) {


        return Objects.isNull(user) ?
                null :
                UserEntity.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .password(user.getPassword())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .role(user.getRole())
                        .creditCard(creditCardMapper.mapListToEntity(user.getCreditCard()))
                        .receivedPayments(paymentMapper.mapListToEntity(user.getReceivedPayments()))
                        .sentPayments(paymentMapper.mapListToEntity(user.getSentPayments()))
                .build();
    }

    public User userEntityToUser (UserEntity userEntity) {
        return Objects.isNull(userEntity) ?
                null :
                User.builder()
                    .id(userEntity.getId())
                    .email(userEntity.getEmail())
                    .password(userEntity.getPassword())
                    .firstName(userEntity.getFirstName())
                    .lastName(userEntity.getLastName())
                    .role(userEntity.getRole())
                    .creditCard(creditCardMapper.mapEntityToList(userEntity.getCreditCard()))
                    .receivedPayments(paymentMapper.mapEntityToList(userEntity.getReceivedPayments()))
                    .sentPayments(paymentMapper.mapEntityToList(userEntity.getSentPayments()))
                .build();
    }
}
