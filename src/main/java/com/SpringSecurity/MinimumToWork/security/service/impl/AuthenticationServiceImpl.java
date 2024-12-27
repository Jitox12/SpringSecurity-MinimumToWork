package com.SpringSecurity.MinimumToWork.security.service.impl;

import com.SpringSecurity.MinimumToWork.exceptionHandler.exception.ObjectNotFoundException;
import com.SpringSecurity.MinimumToWork.repository.model.User;
import com.SpringSecurity.MinimumToWork.security.dto.RegisteredUser;
import com.SpringSecurity.MinimumToWork.security.dto.UserDto;
import com.SpringSecurity.MinimumToWork.security.dto.auth.AuthenticationRequest;
import com.SpringSecurity.MinimumToWork.security.dto.auth.AuthenticationResponse;
import com.SpringSecurity.MinimumToWork.security.service.AuthenticationService;
import com.SpringSecurity.MinimumToWork.security.service.JwtService;
import com.SpringSecurity.MinimumToWork.security.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
   private final JwtService jwtService;
   private final UserService userService;
   private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(JwtService jwtService, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
   public RegisteredUser registerOneUser(UserDto newUser) {
       User user = userService.registerOneUser(newUser);

       RegisteredUser userDto = new RegisteredUser();
       userDto.setId(user.getId());
       userDto.setName(user.getName());
       userDto.setUsername(user.getUsername());
       userDto.setRole(user.getRole().name());

       String jwt = jwtService.generateToken(user, generateExtraClaims(user));
       userDto.setJwt(jwt);

       return userDto;
    }

   @Override
   public Map<String, Object> generateExtraClaims(User user) {
      Map<String, Object> extraClaims = new HashMap<>();
      extraClaims.put("name",user.getName());
      extraClaims.put("role",user.getRole().name());
      extraClaims.put("authorities",user.getAuthorities());

      return extraClaims;
   }

   @Override
   public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
      Authentication authentication = new UsernamePasswordAuthenticationToken(
              authenticationRequest.getEmail(),
              authenticationRequest.getPassword()
      );

      authenticationManager.authenticate(authentication);

      User user = userService.findOneByEmail(authenticationRequest.getEmail()).get();

      String jwt = jwtService.generateToken(user, generateExtraClaims(user));

      AuthenticationResponse authenticationResponse = new AuthenticationResponse();

      authenticationResponse.setJwt(jwt);

      return authenticationResponse;
   }

   @Override
   public boolean validateToken(String jwt) {
      try{
         jwtService.extractEmail(jwt);
         return true;
      }catch (Exception e){
         return false;
      }
   }

   @Override
   public User findLoggedInUser() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String email = (String) auth.getPrincipal();

      return userService.findOneByEmail(email).orElseThrow(()-> new ObjectNotFoundException("User not found. Email: " +email));

   }
}
