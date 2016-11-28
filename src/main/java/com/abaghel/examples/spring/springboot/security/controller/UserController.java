package com.abaghel.examples.spring.springboot.security.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.abaghel.examples.spring.springboot.security.model.UserDetailForm;
import com.abaghel.examples.spring.springboot.security.model.UserEditForm;
import com.abaghel.examples.spring.springboot.security.service.UserService;
import com.abaghel.examples.spring.springboot.security.util.DBEnumUtil;

/**
 * 
 * @author abaghel
 *
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@GetMapping(value = { "/list" })
	public String listUsers(ModelMap model) {
		List<UserEditForm> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "userslist";
	}
	
	@GetMapping(value = { "/delete/{userName}" })
	public String deleteUser(@PathVariable String userName) throws Exception {
		userService.deleteUserByUserName(userName);
		return "redirect:/list";
	}

	@GetMapping(value = { "/", "/welcome" })
	public String welcome(Model model) {
		return "welcome";
	}

	@GetMapping(value = { "/edit/{userName}" })
	public String edit(Model model, @PathVariable String userName) throws Exception{
		UserEditForm userEditForm = userService.findUserForEdit(userName);
		model.addAttribute("userEditForm", userEditForm);
		return "edit";
	}

	@PostMapping(value = "/edit/{userName}")
	public String editUser(@ModelAttribute("userEditForm") @Valid UserEditForm userEditForm,
			BindingResult bindingResult, Model model) throws Exception {
		if (bindingResult.hasErrors()) {
			return "edit";
		}
		userService.update(userEditForm);
		return "redirect:/welcome";
	}

	@PostMapping(value = "/registration")
	public String registrUser(@ModelAttribute("userForm") @Valid UserDetailForm userForm, BindingResult bindingResult,
			Model model) throws Exception {
		if (bindingResult.hasErrors()) {
			return "registration";
		}
		userForm.setRole(DBEnumUtil.Role.USER);
		userService.save(userForm);
		model.addAttribute("message", "User " + userForm.getUserName() + " registered successfully. Please login.");
		return "login";
	}

	@GetMapping(value = "/registration")
	public String registration(Model model) {
		model.addAttribute("userForm", new UserDetailForm());
		return "registration";
	}

	@GetMapping(value = "/login")
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Bad credentials.");

		if (logout != null)
			model.addAttribute("message", "User logged out successfully.");

		return "login";
	}
	
	@GetMapping(value = "/logout")
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}
	
}
