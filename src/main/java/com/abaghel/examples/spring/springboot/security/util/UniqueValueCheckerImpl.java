package com.abaghel.examples.spring.springboot.security.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.abaghel.examples.spring.springboot.security.model.UserDetailForm;
import com.abaghel.examples.spring.springboot.security.service.UserService;
/**
 * 
 * @author abaghel
 *
 */
@Component
public class UniqueValueCheckerImpl implements UniqueValueChecker {
	
	@Autowired
	private UserService userService;
	
	//TODO:: Based on fieldName can check different unique fields like user,email
	public boolean isFieldValueUnique(String fieldName,String fieldValue) {
		UserDetailForm user = userService.findByUserName(fieldValue);
		if (user != null) {
			return false;
		}
		return true;
	}
}
