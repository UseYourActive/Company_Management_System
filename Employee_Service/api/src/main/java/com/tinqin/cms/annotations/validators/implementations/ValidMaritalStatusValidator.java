package com.tinqin.cms.annotations.validators.implementations;

import com.tinqin.cms.annotations.validators.ValidMaritalStatus;
import com.tinqin.cms.enums.MaritalStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidMaritalStatusValidator implements ConstraintValidator<ValidMaritalStatus, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        for (MaritalStatus maritalStatus : MaritalStatus.values()) {
            if (maritalStatus.getMaritalStatus().equals(value)) {
                return true;
            }
        }

        return false;
    }
}
