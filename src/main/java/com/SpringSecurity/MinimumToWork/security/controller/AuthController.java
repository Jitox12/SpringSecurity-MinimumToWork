package com.SpringSecurity.MinimumToWork.security.controller;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.security.dto.user.RegisteredUserDto;
import com.SpringSecurity.MinimumToWork.security.dto.user.RegisterUserDto;
import com.SpringSecurity.MinimumToWork.security.dto.auth.AuthenticationRequest;
import com.SpringSecurity.MinimumToWork.security.dto.auth.AuthenticationResponse;
import com.SpringSecurity.MinimumToWork.security.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    //login
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        AuthenticationResponse rsp = authenticationService.login(authenticationRequest);
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
