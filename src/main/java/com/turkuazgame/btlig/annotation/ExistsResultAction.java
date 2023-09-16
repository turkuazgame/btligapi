package com.turkuazgame.btlig.annotation;

        import com.turkuazgame.btlig.validator.ExistsResultActionValidator;
        import jakarta.validation.Constraint;
        import jakarta.validation.Payload;

        import static java.lang.annotation.ElementType.FIELD;
        import static java.lang.annotation.RetentionPolicy.RUNTIME;

        import java.lang.annotation.Retention;
        import java.lang.annotation.Target;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = { ExistsResultActionValidator.class })
public @interface ExistsResultAction {

    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}