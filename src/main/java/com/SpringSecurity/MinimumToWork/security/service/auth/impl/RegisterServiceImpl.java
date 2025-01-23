package com.SpringSecurity.MinimumToWork.security.service.auth.impl;

import com.SpringSecurity.MinimumToWork.mapper.userEntityMapper.MapperToRegisteredUserDto;
import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.security.dto.user.RegisteredUserDto;
import com.SpringSecurity.MinimumToWork.security.dto.user.RegisterUserDto;
import com.SpringSecurity.MinimumToWork.security.service.auth.RegisterService;
import com.SpringSecurity.MinimumToWork.security.service.jwt.JwtService;
import com.SpringSecurity.MinimumToWork.security.service.user.PostUserService;

public class RegisterServiceImpl implements RegisterService {

    private final MapperToRegisteredUserDto mapperToRegisteredUserDto;

    private final PostUserService postUserService;
    private final JwtService jwtService;

    public RegisterServiceImpl(MapperToRegisteredUserDto mapperToRegisteredUserDto, PostUserService postUserService, JwtService jwtService) {
        this.mapperToRegisteredUserDto = mapperToRegisteredUserDto;
        this.postUserService = postUserService;
        this.jwtService = jwtService;
    }

    @Override
    public RegisteredUserDto registerOneUser(RegisterUserDto newUser) {
        UserEntity user = postUserService.registerOneUser(newUser);

        RegisteredUserDto userDto = mapperToRegisteredUserDto.userEntityToRegisterUserDto(user);

        String jwt = jwtService.generateToken(user, jwtService.generateExtraClaims(user));
        userDto.setJwt(jwt);

        return userDto;
    }
}
