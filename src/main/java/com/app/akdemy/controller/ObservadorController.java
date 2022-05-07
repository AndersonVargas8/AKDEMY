package com.app.akdemy.controller;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.interfacesServices.IEstudianteService;
import com.app.akdemy.interfacesServices.IObservadorService;
import com.app.akdemy.interfacesServices.IProfesorService;
import com.app.akdemy.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ObservadorController {

    @Autowired
    private IObservadorService serObservador;

    @Autowired
    private IEstudianteService serEstudiante;

    @Autowired
    private IProfesorService serProfesor;

    @Autowired
    private UserService serUser;
    
    @GetMapping("/profesor/observador/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESOR')")
    public String getObservacionesEstudiante(@PathVariable Long id, Model model) throws ProfesorNotFound, Exception{

        Estudiante estudiante = serEstudiante.buscarPorId(id);

        model.addAttribute("estudiante", estudiante);
        model.addAttribute("profesor", serProfesor.getByUser(serUser.getLoggedUser()));
        model.addAttribute("observaciones", serObservador.getObservadorEstudiante(estudiante));

        return "profesor/observador/tableObservador";
    }
}
