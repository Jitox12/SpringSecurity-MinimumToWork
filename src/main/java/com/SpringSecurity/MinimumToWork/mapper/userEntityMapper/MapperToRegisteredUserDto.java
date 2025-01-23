package com.SpringSecurity.MinimumToWork.mapper.userEntityMapper;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.security.dto.user.RegisteredUserDto;

public interface MapperToRegisteredUserDto {
    RegisteredUserDto userEntityToRegisterUserDto(UserEntity user);
}
