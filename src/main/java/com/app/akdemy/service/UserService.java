package com.app.akdemy.service;

import com.app.akdemy.Exception.UsernameOrIdNotFound;
import com.app.akdemy.entity.User;

public interface UserService {
    public Iterable<User> getAllUsers();

    public User getUserById(Long id) throws UsernameOrIdNotFound;

    public User getLoggedUser() throws Exception;

    public boolean loggedUserHasRole(String roleName) throws Exception;

    public User guardarUsuario(User user) throws Exception;
}
