package com.SpringSecurity.MinimumToWork.security.service;

import com.SpringSecurity.MinimumToWork.repository.model.User;
import com.SpringSecurity.MinimumToWork.security.dto.UserDto;

import java.util.Optional;

public interface UserService {
    User registerOneUser(UserDto newUser);
    Optional<User> findOneByEmail(String email);
    void validatePassword(UserDto newUser);
}