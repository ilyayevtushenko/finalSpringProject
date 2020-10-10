package com.example.finalSpringProject.model.service.impl;

import com.example.finalSpringProject.model.domain.Payment;
import com.example.finalSpringProject.model.entity.AccountEntity;
import com.example.finalSpringProject.model.entity.PaymentEntity;
import com.example.finalSpringProject.model.entity.UserEntity;
import com.example.finalSpringProject.model.exeptions.InvalidDataRuntimeException;
import com.example.finalSpringProject.model.repository.PaymentRepository;
import com.example.finalSpringProject.model.repository.UserRepository;
import com.example.finalSpringProject.model.service.AccountService;
import com.example.finalSpringProject.model.service.PaymentService;
import com.example.finalSpringProject.model.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentServiceImpl implements PaymentService {

    private final UserRepository userRepository;
    private final AccountService accountService;
    private final UserMapper userMapper;
    private final PaymentRepository paymentRepository;

    @Override
    public void createPayment(String sender, String receiver, BigDecimal sum) {

        UserEntity senderEntity = userRepository.findByCreditCardNumber(sender)
                .orElseThrow(() -> new InvalidDataRuntimeException("Cannot find credit card by this number"));

        UserEntity receiverEntity = userRepository.findByCreditCardNumber(sender)
                .orElseThrow(() -> new InvalidDataRuntimeException("Cannot find credit card by this number"));


        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));

        PaymentEntity paymentEntity = new PaymentEntity();

        paymentEntity.setSender(senderEntity);
        paymentEntity.setReceiver(receiverEntity);
        paymentEntity.setDate(formatter.format(date));
        paymentEntity.setPrice(sum);

        paymentEntity.setPaymentStatus(PaymentEntity.PAYMENT_STATUS.READY);

        accountService.removeBalance(sender, sum);
        accountService.addBalance(receiver, sum);

        paymentEntity.setPaymentStatus(PaymentEntity.PAYMENT_STATUS.SENT);

        paymentRepository.save(paymentEntity);




    }
}
