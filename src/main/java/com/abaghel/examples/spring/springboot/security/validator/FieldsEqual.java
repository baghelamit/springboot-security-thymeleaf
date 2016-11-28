package com.abaghel.examples.spring.springboot.security.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Annotation for cross field validation like jsr 303. This annotation can be 
 * used to compare values of two String fields of a bean like below
 * 
 *\@FieldsEqual(firstField="password", secondField="confirmPassword")
 * public class UserForm 
 * {
 *	 private String password;
 *	 private String confirmPassword;
 * }
 *
 * 
 * @author abaghel
 *
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = FieldsEqualValidator.class)
@Documented
public @interface FieldsEqual {

	String message() default "Field values are not equal";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String firstField();

	String secondField();

}
