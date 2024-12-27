package com.SpringSecurity.MinimumToWork.security.service.impl;

import com.SpringSecurity.MinimumToWork.exceptionHandler.exception.InvalidPasswordException;
import com.SpringSecurity.MinimumToWork.repository.UserRepository;
import com.SpringSecurity.MinimumToWork.repository.model.Role;
import com.SpringSecurity.MinimumToWork.repository.model.User;
import com.SpringSecurity.MinimumToWork.security.dto.UserDto;
import com.SpringSecurity.MinimumToWork.security.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerOneUser(UserDto newUser) {
        validatePassword(newUser);

        User user = new User();
        user.setEmail(newUser.getEmail());
        user.setName(newUser.getName());
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void validatePassword(UserDto newUser) {
        if(!StringUtils.hasText(newUser.getPassword()) || !StringUtils.hasText(newUser.getRepeatPassword())){
            throw new InvalidPasswordException("Password don't match");
        }

        if(!newUser.getPassword().equals(newUser.getRepeatPassword())){
            throw new InvalidPasswordException("Passwords don't match");
        }
    }
}
