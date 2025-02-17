package com.SpringSecurity.MinimumToWork.domain.dao.user;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;

import java.util.Optional;

public interface GetUserDao {
    Optional<UserEntity> findOneByEmail(String email);
}
