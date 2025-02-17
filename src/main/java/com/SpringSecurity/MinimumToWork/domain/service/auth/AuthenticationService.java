package com.SpringSecurity.MinimumToWork.domain.service.auth;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.domain.dto.user.RegisteredUserDto;
import com.SpringSecurity.MinimumToWork.domain.dto.user.RegisterUserDto;

public interface AuthenticationService {
    RegisteredUserDto registerOneUser(RegisterUserDto newUser);
    UserEntity findLoggedInUser();
}
