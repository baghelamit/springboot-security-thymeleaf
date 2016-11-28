package com.abaghel.examples.spring.springboot.security.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

/**
 * Validator class for annotation ValidFile
 * 
 * @author abaghel
 *
 */
public class ValidFileValidator implements ConstraintValidator<ValidFile, MultipartFile> {

	private String[] contentType;
	private long size;

	@Override
	public void initialize(ValidFile constraintAnnotation) {
		this.contentType = constraintAnnotation.contentType();
		this.size = constraintAnnotation.size();
	}

	@Override
	public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
		boolean validFile = false;
		if (value == null || value.isEmpty()) {
			return validFile;
		}
		//validate content-type and size
		if ((Arrays.asList(contentType).contains(value.getContentType())) && ((value.getSize()/1024)) <= size) {
			validFile = true;
		}
		return validFile;
	}

}