package com.SpringSecurity.MinimumToWork.domain.service.auth.impl;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.domain.dto.auth.AuthenticationRequest;
import com.SpringSecurity.MinimumToWork.domain.dto.auth.AuthenticationResponse;
import com.SpringSecurity.MinimumToWork.domain.service.auth.LoginService;
import com.SpringSecurity.MinimumToWork.domain.service.jwt.JwtService;
import com.SpringSecurity.MinimumToWork.domain.service.user.GetUserService;
import com.SpringSecurity.MinimumToWork.domain.utils.jwt.Claims;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;
    private final GetUserService getUserService;
    private final JwtService jwtService;

    public LoginServiceImpl(AuthenticationManager authenticationManager, GetUserService getUserService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.getUserService = getUserService;
        this.jwtService = jwtService;
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

        Authentication authentication = createAuthentication(authenticationRequest);
        authenticationManager.authenticate(authentication);

        UserEntity user = findUserByEmail(authenticationRequest.getEmail());
        String jwt = jwtService.generateToken(user, Claims.generateExtraClaims(user));

        return createAuthenticationResponse(jwt);
    }

    private AuthenticationResponse createAuthenticationResponse(String jwt){
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setJwt(jwt);

        return authenticationResponse;
    }

    private Authentication createAuthentication(AuthenticationRequest authenticationRequest){
        return new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());
    }

    private UserEntity findUserByEmail(String email){
        return getUserService.findOneByEmail(email);
    }
}
