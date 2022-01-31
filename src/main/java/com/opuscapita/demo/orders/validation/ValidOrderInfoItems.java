package com.opuscapita.demo.orders.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidOrderInfoItemsImpl.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOrderInfoItems {

    String message() default "Items can't contain Product duplicates!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
