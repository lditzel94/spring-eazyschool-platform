package com.eazybytes.eazyschool.anotation;

import com.eazybytes.eazyschool.validation.PasswordStrengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint( validatedBy = PasswordStrengthValidator.class )
@Target( { ElementType.METHOD, ElementType.FIELD } )
@Retention( RetentionPolicy.RUNTIME )
public @interface PasswordValidator {
    String message() default "Please choose a stronger password";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
