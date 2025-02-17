package com.SpringSecurity.MinimumToWork.domain.service.user;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.domain.dto.user.RegisterUserDto;

public interface PostUserService {
    UserEntity registerOneUser(RegisterUserDto newUser);
}
