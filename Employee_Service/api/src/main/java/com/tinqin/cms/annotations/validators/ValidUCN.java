package com.tinqin.cms.annotations.validators;

import com.tinqin.cms.annotations.validators.implementations.UCNValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UCNValidator.class)
public @interface ValidUCN {
    String message() default "Invalid Unique Civil Number (UCN)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
