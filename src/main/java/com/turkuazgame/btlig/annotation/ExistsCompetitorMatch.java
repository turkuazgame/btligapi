package com.turkuazgame.btlig.annotation;

import com.turkuazgame.btlig.validator.ExistsCompetitorMatchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = { ExistsCompetitorMatchValidator.class })
public @interface ExistsCompetitorMatch {

    String message() default "CompetitorMatch mevcut değil!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}