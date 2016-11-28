package com.abaghel.examples.spring.springboot.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abaghel.examples.spring.springboot.security.entity.Role;
import com.abaghel.examples.spring.springboot.security.entity.User;
import com.abaghel.examples.spring.springboot.security.repository.UserRepository;
/**
 * 
 * @author abaghel
 *
 */
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username "+username+" not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),getGrantedAuthorities(user));
	}
	
	private Set<GrantedAuthority> getGrantedAuthorities(User user){
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		for (Role role : user.getRoles()) {
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return grantedAuthorities;
	}
	
}
