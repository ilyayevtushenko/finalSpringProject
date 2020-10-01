package com.example.finalSpringProject.model.service.mapper;

import com.example.finalSpringProject.model.domain.Payment;
import com.example.finalSpringProject.model.entity.PaymentEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentMapper {

    private final UserMapper userMapper;
    private final AccountMapper accountMapper;

    public PaymentEntity paymentToPaymentEntity (Payment payment) {
        return Objects.isNull(payment) ?
                null :
                PaymentEntity.builder()
                        .id(payment.getId())
                        .date(payment.getDate())
                        .price(payment.getPrice())
                        .paymentStatus(payment.getPaymentStatus())
                        .receipt(payment.getReceipt())
                        .account(accountMapper.accountToAccountEntity(payment.getAccount()))
                        .sender(userMapper.userToUserEntity(payment.getSender()))
                        .receiver(userMapper.userToUserEntity(payment.getReceiver()))
                        .build();
    }

    public Payment paymentEntityToPayment (PaymentEntity paymentEntity) {
        return Objects.isNull(paymentEntity) ?
                null :
                Payment.builder()
                        .id(paymentEntity.getId())
                        .date(paymentEntity.getDate())
                        .price(paymentEntity.getPrice())
                        .paymentStatus(paymentEntity.getPaymentStatus())
                        .receipt(paymentEntity.getReceipt())
                        .account(accountMapper.accountEntityToAccount(paymentEntity.getAccount()))
                        .sender(userMapper.userEntityToUser(paymentEntity.getSender()))
                        .receiver(userMapper.userEntityToUser(paymentEntity.getReceiver()))
                        .build();
    }

    public List<PaymentEntity> mapListToEntity (List<Payment> payments) {
        return payments.stream()
                .map(this::paymentToPaymentEntity)
                .collect(Collectors.toList());
    }

    public List<Payment> mapEntityToList (List<PaymentEntity> paymentEntities) {
        return paymentEntities.stream()
                .map(this::paymentEntityToPayment)
                .collect(Collectors.toList());
    }

}
