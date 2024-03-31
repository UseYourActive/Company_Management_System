package com.tinqin.cms.annotations.validators.implementations;

import com.tinqin.cms.annotations.validators.ValidBigDecimalString;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class BigDecimalStringValidator implements ConstraintValidator<ValidBigDecimalString, String> {
    @Override
    public void initialize(ValidBigDecimalString constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            new BigDecimal(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
