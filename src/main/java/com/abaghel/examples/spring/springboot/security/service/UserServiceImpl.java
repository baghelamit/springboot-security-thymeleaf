package com.abaghel.examples.spring.springboot.security.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.abaghel.examples.spring.springboot.security.entity.Role;
import com.abaghel.examples.spring.springboot.security.entity.User;
import com.abaghel.examples.spring.springboot.security.model.RoleForm;
import com.abaghel.examples.spring.springboot.security.model.UserDetailForm;
import com.abaghel.examples.spring.springboot.security.model.UserEditForm;
import com.abaghel.examples.spring.springboot.security.repository.RoleRepository;
import com.abaghel.examples.spring.springboot.security.repository.UserRepository;
import com.abaghel.examples.spring.springboot.security.util.DBEnumUtil;
/**
 * 
 * @author abaghel
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void save(UserDetailForm userForm) throws Exception {
		User user = new User();
		BeanUtils.copyProperties(userForm, user, new String[] { "imageFile", "gender" });
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setConfirmPassword(bCryptPasswordEncoder.encode(user.getConfirmPassword()));
		user.setGender(userForm.getGender().name());
		if (userForm.getImageFile() != null) {
			user.setImage(userForm.getImageFile().getBytes());
		}
		Role role = roleRepository.findByRoleName(userForm.getRole().name());
		user.getRoles().add(role);
		userRepository.save(user);
		// login(userForm.getUserName(),userForm.getPassword());
	}

	@Override
	public void update(UserEditForm userEditForm) throws Exception {
		User user = userRepository.findByUserName(userEditForm.getUserName());
		if (user == null) {
			throw new Exception("User " + userEditForm.getUserName() + " not found.");
		}
		BeanUtils.copyProperties(userEditForm, user,new String[] { "userName", "base64EncodedImage", "gender", "password", "confirmPassword", "email" });
		user.setGender(userEditForm.getGender().name());
		userRepository.save(user);
	}

	@Override
	public UserDetailForm findByUserName(String userName){
		UserDetailForm userDetailForm = null;
		User user = userRepository.findByUserName(userName);
		if (user != null) {
		userDetailForm = new UserDetailForm();
		BeanUtils.copyProperties(user, userDetailForm);
		userDetailForm.setGender(DBEnumUtil.Gender.valueOf(user.getGender()));
		}
		return userDetailForm;
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN') or (hasAuthority('USER') and #userName == authentication.name)")
	public UserEditForm findUserForEdit(String userName) throws Exception {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new Exception("User " + userName + " not found.");
		}
		UserEditForm userEditForm = new UserEditForm();
		BeanUtils.copyProperties(user, userEditForm);
		userEditForm.setGender(DBEnumUtil.Gender.valueOf(user.getGender()));
		if (user.getImage() != null) {
			userEditForm.setBase64EncodedImage(Base64.getEncoder().encodeToString(user.getImage()));
		}
		return userEditForm;
	}

	private void login(String username, String password) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, password, userDetails.getAuthorities());
		authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
	}

	@Override
	public void save(RoleForm roleForm) {
		Role role = new Role();
		role.setRoleName(roleForm.getRoleName());
		roleRepository.save(role);
	}

	@Override
	public List<UserEditForm> findAllUsers() {
		List<User> users = userRepository.findAll();
		List<UserEditForm> userList = new ArrayList<UserEditForm>();
		for (User user : users) {
			if (!isAdmin(user.getRoles())) {
				UserEditForm uef = new UserEditForm();
				uef.setUserName(user.getUserName());
				uef.setFirstName(user.getFirstName());
				uef.setLastName(user.getLastName());
				uef.setGender(DBEnumUtil.Gender.valueOf(user.getGender()));
				uef.setDateOfBirth(user.getDateOfBirth());
				if (user.getImage() != null) {
					uef.setBase64EncodedImage(Base64.getEncoder().encodeToString(user.getImage()));
				}
				userList.add(uef);
			}
		}
		return userList;
	}

	private boolean isAdmin(Set<Role> roles) {
		boolean admin = false;
		for (Role role : roles) {
			if (role.getRoleName().equals("ADMIN")) {
				admin = true;
				break;
			}
		}
		return admin;
	}

	@Override
	public void deleteUserByUserName(String userName) throws Exception {
		User user = userRepository.findByUserName(userName);
		if (user == null) {
			throw new Exception("User " + userName + " not found.");
		}
		userRepository.delete(user);
	}
}
