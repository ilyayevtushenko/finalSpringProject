package com.example.finalSpringProject.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
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


    @ManyToOne
    @JoinColumn(name="account_id", nullable=false)
    private AccountEntity accounts;

    @ManyToOne
    @JoinColumn(name="sender_id", insertable = false, updatable = false)
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name="receiver_id", insertable = false, updatable = false)
    private UserEntity receiver;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentEntity)) return false;
        PaymentEntity that = (PaymentEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getPrice(), that.getPrice()) &&
                getPaymentStatus() == that.getPaymentStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDate(), getPrice(), getPaymentStatus());
    }

    public enum PAYMENT_STATUS {
        READY, SENT
    }

//    @Basic(optional = false)
//    @Column(name = "receipt")
//    private File receipt;
}
