package com.SpringSecurity.MinimumToWork.domain.dao.user.impl;

import com.SpringSecurity.MinimumToWork.repository.UserRepository;
import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.domain.dao.user.GetUserDao;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetUserDaoImpl implements GetUserDao {

    private final UserRepository userRepository;

    public GetUserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> findOneByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
