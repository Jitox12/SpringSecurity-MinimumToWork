package com.SpringSecurity.MinimumToWork.domain.service.auth.impl;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.domain.dto.user.RegisteredUserDto;
import com.SpringSecurity.MinimumToWork.domain.dto.user.RegisterUserDto;
import com.SpringSecurity.MinimumToWork.domain.service.auth.AuthenticationService;
import com.SpringSecurity.MinimumToWork.domain.service.jwt.JwtService;
import com.SpringSecurity.MinimumToWork.domain.service.user.GetUserService;
import com.SpringSecurity.MinimumToWork.domain.service.user.PostUserService;
import com.SpringSecurity.MinimumToWork.domain.utils.jwt.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {
   private final JwtService jwtService;
   private final GetUserService getUserService;
   private final PostUserService postUserService;

    public AuthenticationServiceImpl(JwtService jwtService, GetUserService getUserService, PostUserService postUserService) {
        this.jwtService = jwtService;
        this.getUserService = getUserService;
        this.postUserService = postUserService;
    }


    @Override
   public RegisteredUserDto registerOneUser(RegisterUserDto newUser) {
       UserEntity user = postUserService.registerOneUser(newUser);

       RegisteredUserDto userDto = new RegisteredUserDto();
       userDto.setId(user.getId());
       userDto.setName(user.getName());
       userDto.setUsername(user.getUsername());
       userDto.setRole(user.getRole().name());

       String jwt = jwtService.generateToken(user, Claims.generateExtraClaims(user));
       userDto.setJwt(jwt);

       return userDto;
    }

   @Override
   public UserEntity findLoggedInUser() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      String email = (String) auth.getPrincipal();

      return getUserService.findOneByEmail(email);
   }
}
