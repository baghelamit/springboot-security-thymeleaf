package com.abaghel.examples.spring.springboot.security.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import com.abaghel.examples.spring.springboot.security.util.DBEnumUtil.Gender;
import com.abaghel.examples.spring.springboot.security.util.DBEnumUtil.Role;
import com.abaghel.examples.spring.springboot.security.validator.FieldsEqual;
import com.abaghel.examples.spring.springboot.security.validator.UniqueValue;
import com.abaghel.examples.spring.springboot.security.validator.ValidFile;

/**
 * 
 * @author abaghel
 *
 */
@FieldsEqual(firstField="password", secondField="confirmPassword")
public class UserDetailForm {

	private Long id;
	@Size(min = 5, max = 10)
	@UniqueValue(fieldName="userName")
	private String userName;
	@Size(min = 5, max = 10)
	private String password;
	@Size(min = 5, max = 10)
	private String confirmPassword;
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
	@NotEmpty
	@Email
	private String email;
	@ValidFile(contentType={"image/png","image/jpeg"},size=150)
	private MultipartFile imageFile;
	private Role role;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public MultipartFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}


}
