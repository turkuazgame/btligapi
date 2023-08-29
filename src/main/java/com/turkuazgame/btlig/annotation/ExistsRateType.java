package com.turkuazgame.btlig.annotation;

import com.turkuazgame.btlig.validator.ExistsRateTypeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = { ExistsRateTypeValidator.class })
public @interface ExistsRateType {

    String message() default "Rate Type mevcut değil!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}