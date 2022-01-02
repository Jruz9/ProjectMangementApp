package com.app.ProjectManagement.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME) //uses java reflection to get byte code
@Constraint(validatedBy=UniqueValidator.class)
public @interface UniqueValue {

    String message() default  "Unique Constraint violated";
    Class<?> [] group() default{};
    Class<? extends Payload>[] payload() default{};


}
