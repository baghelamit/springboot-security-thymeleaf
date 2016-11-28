package com.abaghel.examples.spring.springboot.security.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * contentType = "images/png"
 * size = size of file in KB
 * 
 * @author abaghel
 *
 */
@Target({ TYPE, ANNOTATION_TYPE, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidFileValidator.class)
@Documented
public @interface ValidFile {

	String message() default "File is not valid";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	String[] contentType();
	
	long size();
}
