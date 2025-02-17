package com.SpringSecurity.MinimumToWork.domain.utils.validators;

import com.SpringSecurity.MinimumToWork.config.exceptionHandler.exception.InvalidPasswordException;
import com.SpringSecurity.MinimumToWork.domain.dto.user.RegisterUserDto;
import org.springframework.util.StringUtils;

public class ValidatePassword {

    public static void validatePassword(RegisterUserDto newUser) {
        ValidatePassword.enterPassword(newUser.getPassword());
        ValidatePassword.enterPassword(newUser.getRepeatPassword());

        ValidatePassword.matchPasswords(newUser.getPassword(), newUser.getRepeatPassword());
    }

    public static void enterPassword(String password) {
        if (!StringUtils.hasText(password)) throw new InvalidPasswordException("You must enter a password");
    }
    public static void matchPasswords(String password, String repeatedPassword){
        if (!password.equals(repeatedPassword)) throw new InvalidPasswordException("Passwords don't match");;
    }
}
