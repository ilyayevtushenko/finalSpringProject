package com.example.finalSpringProject.model.service;

import com.example.finalSpringProject.model.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User registration(User user);

    User findById(Long id);

    User findByEmail(String email);

    User getCurrentUser();
}
