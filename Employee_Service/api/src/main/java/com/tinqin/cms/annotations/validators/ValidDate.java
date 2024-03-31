package com.tinqin.cms.annotations.validators;

import com.tinqin.cms.annotations.validators.implementations.ValidDateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidDateValidator.class})
@Documented
public @interface ValidDate {
    String message() default "Invalid date format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

