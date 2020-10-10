package com.example.finalSpringProject.model.repository;

import com.example.finalSpringProject.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByCreditCardNumber (String creditCardNumber);
    boolean existsByEmail (String email);
}
