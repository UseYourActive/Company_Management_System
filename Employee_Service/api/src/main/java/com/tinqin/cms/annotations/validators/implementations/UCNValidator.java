package com.tinqin.cms.annotations.validators.implementations;

import com.tinqin.cms.annotations.validators.ValidUCN;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UCNValidator implements ConstraintValidator<ValidUCN, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return value.matches("\\d{10}");
    }
}
