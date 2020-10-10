package com.example.finalSpringProject.model.service.mapper;

import com.example.finalSpringProject.model.domain.Payment;
import com.example.finalSpringProject.model.entity.PaymentEntity;
import com.example.finalSpringProject.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PaymentMapper {

    private final ModelMapper modelMapper;

    public PaymentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PaymentEntity paymentToPaymentEntity (Payment payment) {
        return Objects.isNull(payment) ? null : modelMapper.map(payment, PaymentEntity.class);
    }

    public Payment paymentEntityToPayment (PaymentEntity paymentEntity) {
        return Objects.isNull(paymentEntity) ? null : modelMapper.map(paymentEntity, Payment.class);
    }


}
