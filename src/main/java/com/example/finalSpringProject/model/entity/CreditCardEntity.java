package com.example.finalSpringProject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "credit_card")
public class CreditCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Basic(optional = false)
    @Column(name = "number")
    private String number;

    @Basic(optional = false)
    @Column(name = "CVV2")
    private String CVV2;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity users;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accounts_id", referencedColumnName = "id")
    private AccountEntity accounts;

}
