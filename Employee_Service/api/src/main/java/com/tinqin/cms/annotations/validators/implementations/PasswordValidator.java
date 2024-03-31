package com.tinqin.cms.annotations.validators.implementations;

import com.tinqin.cms.annotations.validators.StrongPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<StrongPassword, String> {
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!.,])(?=\\S+$).{8,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null && Pattern.matches(PASSWORD_PATTERN, password);
    }
}
