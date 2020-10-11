package com.example.finalSpringProject.model.repository;

import com.example.finalSpringProject.model.domain.Account;
import com.example.finalSpringProject.model.entity.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    Page<AccountEntity> findAllByNameLike(String name, Pageable pageable);

    Page<AccountEntity> findAllByNameLike(int num, String name, Pageable pageable);

    Page<AccountEntity> findAllById(Long num, Pageable pageable);

}
