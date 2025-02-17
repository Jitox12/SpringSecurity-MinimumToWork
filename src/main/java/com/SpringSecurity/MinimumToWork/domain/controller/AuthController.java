package com.SpringSecurity.MinimumToWork.domain.controller;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.domain.dto.user.RegisteredUserDto;
import com.SpringSecurity.MinimumToWork.domain.dto.user.RegisterUserDto;
import com.SpringSecurity.MinimumToWork.domain.dto.auth.AuthenticationRequest;
import com.SpringSecurity.MinimumToWork.domain.dto.auth.AuthenticationResponse;
import com.SpringSecurity.MinimumToWork.domain.service.auth.AuthenticationService;
import com.SpringSecurity.MinimumToWork.domain.service.auth.LoginService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final LoginService login;

    public AuthController(AuthenticationService authenticationService, LoginService login) {
        this.authenticationService = authenticationService;
        this.login = login;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        AuthenticationResponse rsp = login.login(authenticationRequest);
        return ResponseEntity.ok(rsp);
    }

    @GetMapping("/profile")
    public ResponseEntity<UserEntity> findMyProfile(){
        UserEntity user = authenticationService.findLoggedInUser();
        return ResponseEntity.ok(user);
    }


    @PostMapping("/register")
    public ResponseEntity<RegisteredUserDto> registerOne(@RequestBody @Valid RegisterUserDto newUser){
        RegisteredUserDto registeredUser = authenticationService.registerOneUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

}
