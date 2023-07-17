package com.soni.validators.custom;

import com.soni.validators.custom.validator.BirthDateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = BirthDateValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Documented
public @interface PhoneNumber {
    String message() default "Enter valid phone number";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};
}
