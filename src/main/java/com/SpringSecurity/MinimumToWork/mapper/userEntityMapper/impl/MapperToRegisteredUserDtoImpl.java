package com.SpringSecurity.MinimumToWork.mapper.userEntityMapper.impl;

import com.SpringSecurity.MinimumToWork.mapper.userEntityMapper.MapperToRegisteredUserDto;
import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.security.dto.user.RegisteredUserDto;

public class MapperToRegisteredUserDtoImpl implements MapperToRegisteredUserDto {

    @Override
    public RegisteredUserDto userEntityToRegisterUserDto(UserEntity user) {

        RegisteredUserDto userDto = new RegisteredUserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().name());

        return userDto;
    }
}
