package com.SpringSecurity.MinimumToWork.mapper.registerUserMapper;

import com.SpringSecurity.MinimumToWork.repository.model.Role;
import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.security.dto.user.RegisterUserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class RegisterUserToUserEntityImpl implements RegisterUserToUserEntity{

    private final PasswordEncoder passwordEncoder;

    public RegisterUserToUserEntityImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity registerUserToUserEntity(RegisterUserDto registerUser){

        UserEntity user = new UserEntity();
        user.setEmail(registerUser.getEmail());
        user.setName(registerUser.getName());
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        user.setRole(Role.USER);

        return user;
    }
}
