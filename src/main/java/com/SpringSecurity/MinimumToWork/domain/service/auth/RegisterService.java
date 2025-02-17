package com.SpringSecurity.MinimumToWork.domain.service.auth;

import com.SpringSecurity.MinimumToWork.domain.dto.user.RegisteredUserDto;
import com.SpringSecurity.MinimumToWork.domain.dto.user.RegisterUserDto;

public interface RegisterService {
    RegisteredUserDto registerOneUser(RegisterUserDto newUser);
}
