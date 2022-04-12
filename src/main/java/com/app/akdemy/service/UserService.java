package com.app.akdemy.service;

import com.app.akdemy.Exception.UsernameOrIdNotFound;
import com.app.akdemy.dto.ChangePasswordForm;
import com.app.akdemy.entity.User;

public interface UserService {
    public Iterable<User> getAllUsers();

    public User createUser(User user) throws Exception; 

    public User getUserById(Long id) throws UsernameOrIdNotFound;

    public User updateUser(User user)throws Exception;

    public void deleteUser(Long id) throws UsernameOrIdNotFound;

    public User changePassword(ChangePasswordForm form) throws Exception;

    public User getLoggedUser() throws Exception;
}
