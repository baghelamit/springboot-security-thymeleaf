package com.abaghel.examples.spring.springboot.security.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.abaghel.examples.spring.springboot.security.util.DBEnumUtil.Gender;

/**
 * 
 * @author abaghel
 *
 */
public class UserEditForm {
	private String userName;
	@Size(min = 1, max = 10)
	private String firstName;
	@Size(min = 1, max = 10)
	private String lastName;
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@NotNull
	@Past
	private Date dateOfBirth;	
	@NotNull
	private Gender gender;
	private String base64EncodedImage;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getBase64EncodedImage() {
		return base64EncodedImage;
	}
	public void setBase64EncodedImage(String base64EncodedImage) {
		this.base64EncodedImage = base64EncodedImage;
	}
}
