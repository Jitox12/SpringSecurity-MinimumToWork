package com.SpringSecurity.MinimumToWork.security.service.user;

import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;

public interface GetUserService {
    UserEntity findOneByEmail(String email);
}
