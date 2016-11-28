package com.abaghel.examples.spring.springboot.security.util;

import org.springframework.stereotype.Component;

/**
 * 
 * @author abaghel
 *
 */

public interface UniqueValueChecker {

	public boolean isFieldValueUnique(String fieldName, String fieldValue);
}
