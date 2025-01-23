package com.SpringSecurity.MinimumToWork.security.service.auth.impl;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.security.dto.user.RegisteredUserDto;
import com.SpringSecurity.MinimumToWork.security.dto.user.RegisterUserDto;
import com.SpringSecurity.MinimumToWork.security.dto.auth.AuthenticationRequest;
import com.SpringSecurity.MinimumToWork.security.dto.auth.AuthenticationResponse;
import com.SpringSecurity.MinimumToWork.security.service.auth.AuthenticationService;
import com.SpringSecurity.MinimumToWork.security.service.jwt.JwtService;
import com.SpringSecurity.MinimumToWork.security.service.user.GetUserService;
import com.SpringSecurity.MinimumToWork.security.service.user.PostUserService;
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
   private final GetUserService getUserService;
   private final PostUserService postUserService;
   private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(JwtService jwtService, GetUserService getUserService, PostUserService postUserService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.getUserService = getUserService;
        this.postUserService = postUserService;
        this.authenticationManager = authenticationManager;
    }

    @Override
   public RegisteredUserDto registerOneUser(RegisterUserDto newUser) {
       UserEntity user = postUserService.registerOneUser(newUser);

       RegisteredUserDto userDto = new RegisteredUserDto();
       userDto.setId(user.getId());
       userDto.setName(user.getName());
       userDto.setUsername(user.getUsername());
       userDto.setRole(user.getRole().name());

       String jwt = jwtService.generateToken(user, generateExtraClaims(user));
       userDto.setJwt(jwt);

       return userDto;
    }

   @Override
   public Map<String, Object> generateExtraClaims(UserEntity user) {
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
                                        authenticationRequest.getPassword());

      authenticationManager.authenticate(authentication);

      UserEntity user = getUserService.findOneByEmail(authenticationRequest.getEmail());
      String jwt = jwtService.generateToken(user, generateExtraClaims(user));

      AuthenticationResponse authenticationResponse = new AuthenticationResponse();

      authenticationResponse.setJwt(jwt);

      return authenticationResponse;
   }

   @Override
   public UserEntity findLoggedInUser() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String email = (String) auth.getPrincipal();

      return getUserService.findOneByEmail(email);
   }
}
