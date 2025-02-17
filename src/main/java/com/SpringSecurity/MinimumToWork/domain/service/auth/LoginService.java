package com.SpringSecurity.MinimumToWork.domain.service.auth;

import com.SpringSecurity.MinimumToWork.domain.dto.auth.AuthenticationRequest;
import com.SpringSecurity.MinimumToWork.domain.dto.auth.AuthenticationResponse;

public interface LoginService {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
}
