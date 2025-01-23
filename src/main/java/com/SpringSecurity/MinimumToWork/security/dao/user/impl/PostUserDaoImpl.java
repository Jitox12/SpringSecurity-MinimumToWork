package com.SpringSecurity.MinimumToWork.security.dao.user.impl;

import com.SpringSecurity.MinimumToWork.repository.UserRepository;
import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.security.dao.user.PostUserDao;
import org.springframework.stereotype.Component;

@Component
public class PostUserDaoImpl implements PostUserDao {

    private final UserRepository userRepository;

    public PostUserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity saveOneUser(UserEntity user) {
        return userRepository.save(user);
    }
}
