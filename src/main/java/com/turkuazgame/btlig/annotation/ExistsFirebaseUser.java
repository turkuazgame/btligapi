package com.turkuazgame.btlig.annotation;

import com.turkuazgame.btlig.validator.ExistsFirebaseUserValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = {ExistsFirebaseUserValidator.class })
public @interface ExistsFirebaseUser {

    String message() default "${btlig.constraint.username.ExistsUserUID.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}