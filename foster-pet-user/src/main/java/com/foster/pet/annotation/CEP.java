package com.foster.pet.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.constraints.Pattern;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Pattern(regexp = "[0-9]{5}[-]?[0-9]{3}", message = "O campo 'CEP' é inválido.")
public @interface CEP {
	String message() default "O campo 'CEP' é inválido.";
}
