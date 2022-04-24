package com.app.akdemy.service;

import java.util.Optional;
import java.util.Set;

import com.app.akdemy.Exception.CustomeFieldValidationException;
import com.app.akdemy.Exception.UsernameOrIdNotFound;
import com.app.akdemy.entity.Role;
import com.app.akdemy.entity.User;
import com.app.akdemy.interfacesServices.IRoleService;
import com.app.akdemy.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    IRoleService serRole;

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
    public User getUserById(Long id) throws UsernameOrIdNotFound {
        User user = repUser.findById(id).orElseThrow(() -> new UsernameOrIdNotFound("El Id del usuario no existe"));
        return user;
    }

    protected void mapUser(User from, User to) {
        to.setUsername(from.getUsername());
        to.setRoles(from.getRoles());
        to.setConfirmPassword(to.getPassword());
    }


    public boolean loggedUserHasRole(String roleName) throws Exception {
        //Obtener role con el nombre
        Role role = serRole.buscarPorNombre(roleName);
        //Obtener el usuario logueado
        User usuarioLogueado = this.getLoggedUser();
        
        Set<Role> roles = usuarioLogueado.getRoles();

        if(roles.contains(role))
            return true;
        
        return false;
    }
    
    @Override
    public User getLoggedUser() throws Exception {
        //Obtener el usuario logueado
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

    @Override
    public User guardarUsuario(User user) {
        return repUser.save(user);
    }

    @Override
    public Iterable<User> getAvailableUsersProfesores() {
        return repUser.usersAvaliablesProfesores();
    }
}
