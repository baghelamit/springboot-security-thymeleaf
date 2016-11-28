package com.abaghel.examples.spring.springboot.security.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.abaghel.examples.spring.springboot.security.util.UniqueValueChecker;

/**
 * Validator class for annotation UniqueValue. Based on "fieldName" this annotation can
 * be used for different field like userName, email etc. UniqueValueChecker class uses 
 * "fieldName" to check unique value for different field.
 * 
 * @author abaghel
 *
 */
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String> {

	@Autowired
	private UniqueValueChecker uvt;
	
	private String fieldName;

	@Override
	public void initialize(UniqueValue constraintAnnotation) {
		this.fieldName = constraintAnnotation.fieldName();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return uvt.isFieldValueUnique(fieldName,value);
	}

}