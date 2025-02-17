package com.SpringSecurity.MinimumToWork.config.mapper.registerUserMapper;

import com.SpringSecurity.MinimumToWork.repository.model.Role;
import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.domain.dto.user.RegisterUserDto;

public class RegisterUserDtoMapper {

    public static UserEntity toUserEntity(RegisterUserDto registerUser){

        UserEntity user = new UserEntity();
        user.setEmail(registerUser.getEmail());
        user.setName(registerUser.getName());
        user.setPassword(registerUser.getPassword());
        user.setRole(Role.USER);

        return user;
    }
}
