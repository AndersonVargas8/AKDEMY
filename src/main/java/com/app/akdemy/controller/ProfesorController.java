package com.app.akdemy.controller;

import javax.validation.Valid;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.Exception.UsernameOrIdNotFound;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.interfacesServices.IProfesorService;
import com.app.akdemy.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfesorController {

    @Autowired
    private IProfesorService serProfesor;

    @Autowired
    private UserService serUser;

    // controlador de profesor desde coordinador
    @GetMapping("/coordinador/profesores")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String index(Model model) {

        model.addAttribute("profesor", new Profesor());
        model.addAttribute("profesores", serProfesor.getAllProfesors());
        model.addAttribute("users", serUser.getAvailableUsersProfesores());
        model.addAttribute("itemNavbar", "profesores");
        return "coordinador/profesores/index";
    }

    @PostMapping("/saveprofesor")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String createProfesor(@Valid @ModelAttribute("profesor") Profesor profesor, Model model)
            throws UsernameOrIdNotFound {

        profesor.setUsuario(serUser.getUserById(profesor.getUsuario().getId()));
        serProfesor.saveProfesor(profesor);
        serUser.setRoleProfesor(profesor.getUsuario());
        return "redirect:/coordinador/profesores";
    }

    @GetMapping("/coordinador/profesores/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String getEditarProfesor(@PathVariable long id, Model model) throws ProfesorNotFound {
        Profesor profesor = serProfesor.getById(id);
        model.addAttribute("editarProfesor", profesor);
        model.addAttribute("profesores", serProfesor.getAllProfesors());
        model.addAttribute("users", serUser.getAvailableUsersProfesores());
        model.addAttribute("itemNavbar", "profesores");
        return "coordinador/profesores/editarProfesor.html";
    }

    @GetMapping("/coordinador/eliminarProfesor/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String deleteProfesor(@PathVariable long id, Model model) throws ProfesorNotFound {
        try {
            Profesor profesor = serProfesor.getById(id);
            serUser.removeRoleProfesor(profesor.getUsuario());
            serProfesor.deleteProfesor(profesor);
        } catch (Exception e) {
            model.addAttribute("deleteError", "No se puede borrar el usuario");
        }
        return "redirect:/coordinador/profesores";
    }

    // controlador profesor
    @GetMapping("/profesor")
    @PreAuthorize("hasAnyRole('ROLE_PROFESOR', 'ROLE_ADMIN')")
    public String inicioCoordinador(Model model) {
        model.addAttribute("itemNavbar", "inicio");
        return "profesor/index";
    }

    @GetMapping("/profesor/horario")
    @PreAuthorize("hasAnyRole('ROLE_PROFESOR', 'ROLE_ADMIN')")
    public String verHorarioProfesor(Model model) {
        model.addAttribute("itemNavbar", "horario");
        return "profesor/horario/index";
    }

    @GetMapping("/profesor/cursos")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESOR')")
    public String verCursosProfesor(Model model) {
        model.addAttribute("itemNavbar", "cursos");
        return "profesor/cursos/index";
    }
}
