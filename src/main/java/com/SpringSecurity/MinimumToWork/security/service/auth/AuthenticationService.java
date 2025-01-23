package com.SpringSecurity.MinimumToWork.security.service.auth;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.security.dto.user.RegisteredUserDto;
import com.SpringSecurity.MinimumToWork.security.dto.user.RegisterUserDto;
import com.SpringSecurity.MinimumToWork.security.dto.auth.AuthenticationRequest;
import com.SpringSecurity.MinimumToWork.security.dto.auth.AuthenticationResponse;

import java.util.Map;

public interface AuthenticationService {
    public RegisteredUserDto registerOneUser(RegisterUserDto newUser);

    public Map<String, Object> generateExtraClaims(UserEntity user);

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest);

    public UserEntity findLoggedInUser();
}
