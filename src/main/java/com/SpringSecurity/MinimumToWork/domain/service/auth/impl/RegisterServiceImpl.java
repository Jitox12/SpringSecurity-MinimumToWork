package com.SpringSecurity.MinimumToWork.domain.service.auth.impl;

import com.SpringSecurity.MinimumToWork.config.mapper.userEntityMapper.UserEntityMapper;
import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.domain.dto.user.RegisteredUserDto;
import com.SpringSecurity.MinimumToWork.domain.dto.user.RegisterUserDto;
import com.SpringSecurity.MinimumToWork.domain.service.auth.RegisterService;
import com.SpringSecurity.MinimumToWork.domain.service.jwt.JwtService;
import com.SpringSecurity.MinimumToWork.domain.service.user.PostUserService;

public class RegisterServiceImpl implements RegisterService {


    private final PostUserService postUserService;
    private final JwtService jwtService;

    public RegisterServiceImpl(PostUserService postUserService, JwtService jwtService) {
        this.postUserService = postUserService;
        this.jwtService = jwtService;
    }


    @Override
    public RegisteredUserDto registerOneUser(RegisterUserDto newUser) {
        UserEntity user = postUserService.registerOneUser(newUser);

        RegisteredUserDto userDto = UserEntityMapper.toRegisteredUserDto(user);

        String jwt = jwtService.generateToken(user, jwtService.generateExtraClaims(user));
        userDto.setJwt(jwt);

        return userDto;
    }
}
