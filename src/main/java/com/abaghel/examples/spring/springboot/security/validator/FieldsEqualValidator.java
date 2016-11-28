package com.abaghel.examples.spring.springboot.security.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Validator class for annotation FieldMatch
 * 
 * @author abaghel
 *
 */
public class FieldsEqualValidator implements ConstraintValidator<FieldsEqual, Object> {

	private String firstFieldName;
	private String secondFieldName;
	private String message;

	@Override
	public void initialize(FieldsEqual constraintAnnotation) {
		this.firstFieldName = constraintAnnotation.firstField();
		this.secondFieldName = constraintAnnotation.secondField();
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean matchingFields = false;
		try {
			final String firstFieldValue = BeanUtils.getProperty(value, firstFieldName);
			final String secondFieldValue = BeanUtils.getProperty(value, secondFieldName);
			matchingFields = firstFieldValue == null && secondFieldValue == null || firstFieldValue != null && firstFieldValue.equals(secondFieldValue);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!matchingFields) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addNode(secondFieldName).addConstraintViolation();
		}
		return matchingFields;
	}

}