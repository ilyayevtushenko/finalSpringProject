package com.example.finalSpringProject.model.service.mapper;

import com.example.finalSpringProject.model.domain.User;
import com.example.finalSpringProject.model.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserEntity userToUserEntity (User user) {

        return Objects.isNull(user) ? null : modelMapper.map(user, UserEntity.class);

    }

    public User userEntityToUser (UserEntity userEntity) {

        return Objects.isNull(userEntity) ? null : modelMapper.map(userEntity, User.class);

    }
}
