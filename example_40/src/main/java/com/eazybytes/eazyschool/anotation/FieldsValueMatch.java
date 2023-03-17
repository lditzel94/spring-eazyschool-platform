package com.eazybytes.eazyschool.anotation;

import com.eazybytes.eazyschool.validation.FieldsValueMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Constraint( validatedBy = FieldsValueMatchValidator.class )
@Target( { ElementType.TYPE } )
@Retention( RetentionPolicy.RUNTIME )
public @interface FieldsValueMatch {

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String message() default "Fields values don't match!";

    String field();

    String fieldMatch();

    @Target( {ElementType.TYPE} )
    @Retention( RetentionPolicy.RUNTIME )
    @interface List{
        FieldsValueMatch[] value();
    }
}
