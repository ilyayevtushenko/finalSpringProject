package com.example.finalSpringProject.model.domain;

import com.example.finalSpringProject.model.entity.AccountEntity;
import com.example.finalSpringProject.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Builder
public class CreditCard {

    private Long id;
    private String number;
    private String CVV2;
    private UserEntity owner;
    private AccountEntity account;

}
