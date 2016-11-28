package com.abaghel.examples.spring.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abaghel.examples.spring.springboot.security.entity.User;
/**
 * 
 * @author abaghel
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
}
