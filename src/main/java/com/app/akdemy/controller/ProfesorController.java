package com.app.akdemy.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.Exception.UsernameOrIdNotFound;
import com.app.akdemy.entity.HorarioCurso;
import com.app.akdemy.entity.MateriaGrado;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.entity.User;
import com.app.akdemy.interfacesServices.IHorarioService;
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

    @Autowired
    private IHorarioService serHorario;

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

    // controlador profesor
    @GetMapping("/profesor")
    @PreAuthorize("hasAnyRole('ROLE_PROFESOR', 'ROLE_ADMIN')")
    public String inicioCoordinador(Model model) {
        model.addAttribute("itemNavbar", "inicio");
        return "profesor/index";
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

    @GetMapping("/profesor/horario")
    @PreAuthorize("hasAnyRole('ROLE_PROFESOR', 'ROLE_ADMIN')")
    public String verHorarioProfesor(Model model) throws Exception {

        model.addAttribute("itemNavbar", "horario");
        model = cargarTablaHorarios(model);
        return "profesor/horario/index";
    }

    private Model cargarTablaHorarios(Model model) throws Exception {
        User user = serUser.getLoggedUser();
        Profesor profesor;
        try {
            profesor = serProfesor.getByUser(user);
        } catch (Exception e) {
            return model;
        }
        // Obtener los horarios
        List<HorarioCurso> horarios = serHorario.obtenerPorProfesor(profesor);

        model.addAttribute("horarios", horarios);
        // Crear las horas del horario
        List<String> horas = Arrays.asList("06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17");

        model.addAttribute("horas", horas);

        model.addAttribute("nombreProfesor", profesor.getNombres().concat(" " + profesor.getApellidos()));
        return model;
    }

    @GetMapping("/profesor/observador")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESOR')")
    public String verObservacionProfesor(Model model) {
        model.addAttribute("itemNavbar", "observador");
        return "profesor/observador/index";
    }
}
