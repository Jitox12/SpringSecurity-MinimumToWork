package com.SpringSecurity.MinimumToWork.mapper.registerUserMapper;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.security.dto.user.RegisterUserDto;

public interface RegisterUserToUserEntity {
    UserEntity registerUserToUserEntity(RegisterUserDto registerUser);
}
