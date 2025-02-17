package com.SpringSecurity.MinimumToWork.config.mapper.userEntityMapper;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.domain.dto.user.RegisteredUserDto;

public class UserEntityMapper {

    public static RegisteredUserDto toRegisteredUserDto(UserEntity user) {

        RegisteredUserDto userDto = new RegisteredUserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().name());

        return userDto;
    }
}
