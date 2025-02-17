package com.SpringSecurity.MinimumToWork.domain.dao.user;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;

public interface PostUserDao {
    UserEntity saveOneUser(UserEntity user);
}
