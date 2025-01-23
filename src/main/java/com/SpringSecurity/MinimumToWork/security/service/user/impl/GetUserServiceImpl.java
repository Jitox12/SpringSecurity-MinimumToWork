package com.SpringSecurity.MinimumToWork.security.service.user.impl;

import com.SpringSecurity.MinimumToWork.exceptionHandler.exception.ObjectNotFoundException;
import com.SpringSecurity.MinimumToWork.repository.model.UserEntity;
import com.SpringSecurity.MinimumToWork.security.dao.user.GetUserDao;
import com.SpringSecurity.MinimumToWork.security.service.user.GetUserService;
import org.springframework.stereotype.Service;

@Service
public class GetUserServiceImpl implements GetUserService {

    private final GetUserDao userDao;

    public GetUserServiceImpl(GetUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserEntity findOneByEmail(String email) {
        return userDao.findOneByEmail(email).orElseThrow(() -> new ObjectNotFoundException("User not found. Username: "+ email));
    }
}
