package com.example.finalSpringProject.model.repository;

import com.example.finalSpringProject.model.domain.Account;
import com.example.finalSpringProject.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
