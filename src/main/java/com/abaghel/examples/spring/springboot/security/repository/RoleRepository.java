package com.abaghel.examples.spring.springboot.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abaghel.examples.spring.springboot.security.entity.Role;
/**
 * 
 * @author abaghel
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByRoleName(String roleName);
}
