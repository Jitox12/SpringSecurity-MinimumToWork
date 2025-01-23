package com.SpringSecurity.MinimumToWork.security.service.user;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.security.dto.user.RegisterUserDto;

public interface PostUserService {
    UserEntity registerOneUser(RegisterUserDto newUser);
}
