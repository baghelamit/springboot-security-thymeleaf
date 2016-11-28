package com.abaghel.examples.spring.springboot.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abaghel.examples.spring.springboot.security.entity.RememberMeToken;
/**
 * 
 * @author abaghel
 *
 */
public interface RememberMeTokenRepository extends JpaRepository<RememberMeToken, String> {
	List<RememberMeToken> findByUserName(String userName);
	RememberMeToken findBySeries(String series);
}
