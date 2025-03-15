package org.equinox.apirest.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UsernameUniqueValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface IsUserNameUnique {
    String message() default "{unique.username.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
