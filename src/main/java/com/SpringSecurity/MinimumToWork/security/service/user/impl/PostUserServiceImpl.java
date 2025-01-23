package com.SpringSecurity.MinimumToWork.security.service.user.impl;

import com.SpringSecurity.MinimumToWork.mapper.registerUserMapper.RegisterUserToUserEntity;
import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.security.dao.user.PostUserDao;
import com.SpringSecurity.MinimumToWork.security.dto.user.RegisterUserDto;
import com.SpringSecurity.MinimumToWork.security.service.user.PostUserService;
import com.SpringSecurity.MinimumToWork.utils.ValidatePassword;
import org.springframework.stereotype.Service;

@Service
public class PostUserServiceImpl implements PostUserService {

    private final PostUserDao postUserDao;
    private final RegisterUserToUserEntity registerUserToUserEntity;

    public PostUserServiceImpl(PostUserDao postUserDao, RegisterUserToUserEntity registerUserToUserEntity) {
        this.postUserDao = postUserDao;
        this.registerUserToUserEntity = registerUserToUserEntity;
    }

    @Override
    public UserEntity registerOneUser(RegisterUserDto registerUser) {
        ValidatePassword.validatePassword(registerUser);
        UserEntity user = registerUserToUserEntity.registerUserToUserEntity(registerUser);
        return postUserDao.saveOneUser(user);
    }
}
