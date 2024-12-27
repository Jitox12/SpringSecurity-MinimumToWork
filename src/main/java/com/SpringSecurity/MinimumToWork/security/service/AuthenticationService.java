package com.SpringSecurity.MinimumToWork.security.service;

import com.SpringSecurity.MinimumToWork.repository.model.User;
import com.SpringSecurity.MinimumToWork.security.dto.RegisteredUser;
import com.SpringSecurity.MinimumToWork.security.dto.UserDto;
import com.SpringSecurity.MinimumToWork.security.dto.auth.AuthenticationRequest;
import com.SpringSecurity.MinimumToWork.security.dto.auth.AuthenticationResponse;

import java.util.Map;

public interface AuthenticationService {
    public RegisteredUser registerOneUser(UserDto newUser);

    public Map<String, Object> generateExtraClaims(User user);

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest);

    public boolean validateToken(String jwt);

    public User findLoggedInUser();
}
