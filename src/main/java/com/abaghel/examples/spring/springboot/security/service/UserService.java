package com.abaghel.examples.spring.springboot.security.service;

import java.util.List;

import com.abaghel.examples.spring.springboot.security.model.RoleForm;
import com.abaghel.examples.spring.springboot.security.model.UserDetailForm;
import com.abaghel.examples.spring.springboot.security.model.UserEditForm;
/**
 * 
 * @author abaghel
 *
 */
public interface UserService {
    void save(UserDetailForm userForm)throws Exception;
    UserDetailForm findByUserName(String userName);
    UserEditForm findUserForEdit(String userName)throws Exception;
    void update(UserEditForm userDetailForm)throws Exception;
    void save(RoleForm roleForm);
    List<UserEditForm> findAllUsers();
    void deleteUserByUserName(String userName)throws Exception;
}
