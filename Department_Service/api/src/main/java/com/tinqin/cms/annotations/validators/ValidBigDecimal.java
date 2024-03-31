package com.tinqin.cms.annotations.validators;

import com.tinqin.cms.annotations.validators.implementations.ValidBigDecimalValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidBigDecimalValidator.class})
@Documented
public @interface ValidBigDecimal {
    String message() default "Invalid BigDecimal format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

