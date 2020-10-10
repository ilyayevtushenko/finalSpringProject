package com.example.finalSpringProject.model.domain;

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
    private User user;
    private Account account;

}
