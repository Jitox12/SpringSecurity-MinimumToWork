package com.SpringSecurity.MinimumToWork.security.service.auth;

import com.SpringSecurity.MinimumToWork.security.dto.user.RegisteredUserDto;
import com.SpringSecurity.MinimumToWork.security.dto.user.RegisterUserDto;

public interface RegisterService {
    RegisteredUserDto registerOneUser(RegisterUserDto newUser);
}
