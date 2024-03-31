package com.tinqin.cms.annotations.validators;

import com.tinqin.cms.annotations.validators.implementations.BigDecimalStringValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BigDecimalStringValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBigDecimalString {
    String message() default "Invalid BigDecimal format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
