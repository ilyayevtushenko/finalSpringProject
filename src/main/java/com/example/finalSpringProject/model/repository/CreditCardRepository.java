package com.example.finalSpringProject.model.repository;

import com.example.finalSpringProject.model.domain.User;
import com.example.finalSpringProject.model.entity.AccountEntity;
import com.example.finalSpringProject.model.entity.CreditCardEntity;
import com.example.finalSpringProject.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {
    Optional<CreditCardEntity> findByNumber (String number);
    Set<CreditCardEntity> findAllByUsers (UserEntity userEntity);

}
