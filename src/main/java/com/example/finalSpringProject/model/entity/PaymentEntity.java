package com.example.finalSpringProject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "date")
    private String date;

    @Basic(optional = false)
    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column (name = "payment_status")
    private PAYMENT_STATUS paymentStatus;

    @Basic(optional = false)
    @Column(name = "receipt")
    private File receipt;

    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private AccountEntity accounts;

    @ManyToOne
    @JoinColumn(name="sender_id", insertable = false, updatable = false)
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name="receiver_id", insertable = false, updatable = false)
    private UserEntity receiver;

    public enum PAYMENT_STATUS {
        READY, SENT
    }
}
