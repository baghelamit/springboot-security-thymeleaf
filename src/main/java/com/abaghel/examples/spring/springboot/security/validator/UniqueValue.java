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
 * Annotation to check if field is unique.
 * 
 * @UniqueValue(fieldName="userName")
 * 
 * @author abaghel
 *
 */
@Target({ TYPE, ANNOTATION_TYPE, FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueValueValidator.class)
@Documented
public @interface UniqueValue {

	String message() default "Value already exists";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	String fieldName();

}
