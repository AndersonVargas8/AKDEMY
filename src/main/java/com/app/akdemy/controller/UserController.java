package com.app.akdemy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.akdemy.entity.User;
import com.app.akdemy.interfacesServices.IRoleService;
import com.app.akdemy.service.UserService;

@Controller
public class UserController {

    @Autowired
    UserService serUser;

    @Autowired
    IRoleService serRole;

    @GetMapping("/")
    public String inicio() {
        User user;
        try {
            user = serUser.getLoggedUser();
        } catch (Exception e) {
            return "redirect:/login";
        }
        if (user != null) {
            return "redirect:/conmutador";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String index() {
        return "login";
    }

    @GetMapping("/conmutador")
    public String conmutador() throws Exception {
        User usuarioLogueado = serUser.getLoggedUser();

        // Si el usuario es admin
        if (serUser.loggedUserHasRole("ADMIN"))
            return "redirect:/seleccionarRol";

        // Si tiene varios roles
        if (usuarioLogueado.getRoles().size() > 1)
            return "redirect:/seleccionarRol";

        // Si el usuario es coordinador
        if (serUser.loggedUserHasRole("COORDINADOR"))
            return "redirect:/coordinador";

        // Si el usuario es profesor
        if (serUser.loggedUserHasRole("PROFESOR"))
            return "redirect:/profesor";

        // Si el usuario es acudiente
        if (serUser.loggedUserHasRole("ACUDIENTE"))
            return "redirect:/acudiente";

        // Si el usuario es estudiante
        if (serUser.loggedUserHasRole("ESTUDIANTE"))
            return "redirect:/estudiante";

        return "redirect:/seleccionarRol";
    }

    @GetMapping("/seleccionarRol")
    public String seleccionarRol(Model model) throws Exception {
        User usuarioLogueado = serUser.getLoggedUser();
        if (serUser.loggedUserHasRole("ADMIN"))
            model.addAttribute("roles", serRole.obtenerTodos());
        else
            model.addAttribute("roles", usuarioLogueado.getRoles());

        return "seleccionarRol";
    }

    @RequestMapping(value = "/user/verificarUsuario", method = RequestMethod.POST)
    public ResponseEntity<?> verificarUsuario(@RequestBody String usuario) {
        try {
            boolean validacion = serUser.validarUsuario(new User(usuario, ""));
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

}