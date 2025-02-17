package com.SpringSecurity.MinimumToWork.domain.service.user.impl;

import com.SpringSecurity.MinimumToWork.config.mapper.registerUserMapper.RegisterUserDtoMapper;
import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.domain.dao.user.PostUserDao;
import com.SpringSecurity.MinimumToWork.domain.dto.user.RegisterUserDto;
import com.SpringSecurity.MinimumToWork.domain.service.user.PostUserService;
import com.SpringSecurity.MinimumToWork.domain.utils.validators.ValidatePassword;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PostUserServiceImpl implements PostUserService {

    private final PostUserDao postUserDao;
    private final PasswordEncoder passwordEncoder;

    public PostUserServiceImpl(PostUserDao postUserDao, PasswordEncoder passwordEncoder) {
        this.postUserDao = postUserDao;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserEntity registerOneUser(RegisterUserDto registerUser) {
        ValidatePassword.validatePassword(registerUser);

        registerUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));

        UserEntity user = RegisterUserDtoMapper.toUserEntity(registerUser);
        return postUserDao.saveOneUser(user);
    }

}
