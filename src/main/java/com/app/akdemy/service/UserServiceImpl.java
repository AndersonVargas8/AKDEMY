package com.app.akdemy.service;

import java.util.Optional;

import com.app.akdemy.Exception.CustomeFieldValidationException;
import com.app.akdemy.Exception.UsernameOrIdNotFound;
import com.app.akdemy.dto.ChangePasswordForm;
import com.app.akdemy.entity.User;
import com.app.akdemy.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repUser;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Iterable<User> getAllUsers() {
        return repUser.findAll();
    }

    private boolean checkUserNameAvailable(User user) throws Exception {
        Optional<User> userFound = repUser.findByUsername(user.getUsername());

        if (userFound.isPresent()) {
            throw new CustomeFieldValidationException("Nombre de usuario no disponible","username");
        }
        return true;
    }

    private boolean checkPasswordValid(User user) throws Exception {
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new Exception("Las contraseñas no coinciden");
        }
        return true;
    }

    @Override
    public User createUser(User user) throws Exception {
        if (checkUserNameAvailable(user) && checkPasswordValid(user)) {
            String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
            user.setPassword(encodePassword);
            user = repUser.save(user);
        }

        return user;
    }

    @Override
    public User getUserById(Long id) throws UsernameOrIdNotFound {
        User user = repUser.findById(id).orElseThrow(() -> new UsernameOrIdNotFound("El Id del usuario no existe"));
        return user;
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public User updateUser(User fromUser) throws Exception {
        User toUser = getUserById(fromUser.getId());
        mapUser(fromUser, toUser);
        User user = repUser.save(toUser);
        return user;
    }

    protected void mapUser(User from, User to) {
        to.setUsername(from.getUsername());
        to.setRoles(from.getRoles());
        to.setConfirmPassword(to.getPassword());
    }

    @Override
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public void deleteUser(Long id) throws UsernameOrIdNotFound {
        User user = getUserById(id);

        repUser.delete(user);
    }

    public boolean isLoggedUserADMIN() {
        return loggedUserHasRole("ROLE_ADMIN");
    }

    public boolean loggedUserHasRole(String role) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails loggedUser = null;
        Object roles = null;
        if (principal instanceof UserDetails) {
            loggedUser = (UserDetails) principal;

            roles = loggedUser.getAuthorities().stream()
                    .filter(x -> role.equals(x.getAuthority()))
                    .findFirst().orElse(null); // loggedUser = null;
        }
        return roles != null ? true : false;
    }

    @Override
    public User changePassword(ChangePasswordForm form) throws Exception {
        User storedUser = getUserById(form.getId());
        if (!isLoggedUserADMIN() && !passwordEncoder.matches(form.getCurrentPassword(), storedUser.getPassword())) {
            throw new Exception("Contraseña actual incorrecta.");
        }

        if (!isLoggedUserADMIN() && form.getCurrentPassword().equals(form.getNewPassword())) {
            throw new Exception("La nueva contraseña debe ser diferente a la actual contraseña");
        }

        if (!form.getNewPassword().equals(form.getConfirmPassword())) {
            throw new Exception("Las contraseñas no coinciden");
        }

        String encodePassword = bCryptPasswordEncoder.encode(form.getNewPassword());
        storedUser.setPassword(encodePassword);
        storedUser.setConfirmPassword(encodePassword);
        return repUser.save(storedUser);
    }
    
    @Override
    public User getLoggedUser() throws Exception {
        //Obtener el usuario logeado
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        UserDetails loggedUser = null;
    
        //Verificar que ese objeto traido de sesion es el usuario
        if (principal instanceof UserDetails) {
            loggedUser = (UserDetails) principal;
        }
        
        User myUser = repUser
                .findByUsername(loggedUser.getUsername()).orElseThrow(() -> new Exception("Ocurrió un problema al obtener el usuario de la sesión"));
        
        return myUser;
    }
}
