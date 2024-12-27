package com.SpringSecurity.MinimumToWork.security.controller;

import com.SpringSecurity.MinimumToWork.repository.model.User;
import com.SpringSecurity.MinimumToWork.security.dto.RegisteredUser;
import com.SpringSecurity.MinimumToWork.security.dto.UserDto;
import com.SpringSecurity.MinimumToWork.security.dto.auth.AuthenticationRequest;
import com.SpringSecurity.MinimumToWork.security.dto.auth.AuthenticationResponse;
import com.SpringSecurity.MinimumToWork.security.service.AuthenticationService;
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

    //Validator
    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){

        boolean isTokenValid = authenticationService.validateToken(jwt);

        return ResponseEntity.ok(isTokenValid);
    }

    //login
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest){
        AuthenticationResponse rsp = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(rsp);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> findMyProfile(){
        User user = authenticationService.findLoggedInUser();
        return ResponseEntity.ok(user);
    }


    @PostMapping("/register")
    public ResponseEntity<RegisteredUser> registerOne(@RequestBody @Valid UserDto newUser){
        RegisteredUser registeredUser = authenticationService.registerOneUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

}
