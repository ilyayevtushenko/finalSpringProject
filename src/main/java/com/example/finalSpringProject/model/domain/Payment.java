package com.example.finalSpringProject.model.domain;

import com.example.finalSpringProject.model.entity.AccountEntity;
import com.example.finalSpringProject.model.entity.PaymentEntity;
import com.example.finalSpringProject.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
@NoArgsConstructor
@Builder
public class Payment {


    private Long id;


    private String date;

    @PositiveOrZero
    private BigDecimal price;

    private PaymentEntity.PAYMENT_STATUS paymentStatus;
    private File receipt;
    private Account account;
    private User sender;
    private User receiver;
}
