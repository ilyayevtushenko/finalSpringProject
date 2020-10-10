package com.example.finalSpringProject.model.service.impl;

import com.example.finalSpringProject.model.domain.User;
import com.example.finalSpringProject.model.entity.UserEntity;
import com.example.finalSpringProject.model.exeptions.EntityNotFoundRuntimeException;
import com.example.finalSpringProject.model.exeptions.InvalidDataRuntimeException;
import com.example.finalSpringProject.model.repository.UserRepository;
import com.example.finalSpringProject.model.service.UserService;
import com.example.finalSpringProject.model.service.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User registration(User user) throws RuntimeException {

        if (userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("email or login is already taken");
        }

        user.setRole(UserEntity.ROLE.USER);

        user.setPassword(encoder.encode(user.getPassword()));
        UserEntity userEntity = userMapper.userToUserEntity(user);
        UserEntity saveEntity = userRepository.save(userEntity);

        return userMapper.userEntityToUser(saveEntity);

    }

    @Override
    public User findById(Long id) {
        return userMapper.userEntityToUser(userRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundRuntimeException("Don't find user by this id")));
    }

    @Override
    public User findByEmail(String email) {
        return userMapper.userEntityToUser(userRepository.findByEmail(email).
                orElseThrow(() -> new EntityNotFoundRuntimeException("Don't find user by this email")));
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        if (Objects.isNull(email)) {
            log.warn("Login is empty");
            throw new EntityNotFoundRuntimeException("Login is empty");
        }
        Optional<UserEntity> byLogin = userRepository.findByEmail(email);

        return byLogin.map(userMapper::userEntityToUser).orElse(null);
    }

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }
}
