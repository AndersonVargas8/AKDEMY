package com.app.akdemy.controller;

import java.sql.Date;
import java.util.Calendar;

import javax.validation.Valid;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.Observador;
import com.app.akdemy.interfacesServices.IEstudianteService;
import com.app.akdemy.interfacesServices.IObservadorService;
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

    @GetMapping("/profesor/observador/new/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESOR')")
    public String getFormObservador(@PathVariable Long id, Model model) throws ProfesorNotFound, Exception{

        Observador observador = new Observador();
        java.sql.Date timeNow = new Date(Calendar.getInstance().getTimeInMillis());

        observador.setEstudiante(serEstudiante.buscarPorId(id));
        observador.setProfesor(serProfesor.getByUser(serUser.getLoggedUser()));
        observador.setFecha(timeNow);

        model.addAttribute("observacion", observador);

        return "profesor/observador/form.html";
    }


    @PostMapping("/profesor/saveobservador")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESOR')")
    public String newObservacion(@Valid @ModelAttribute("observacion") Observador observador, Model model) throws ProfesorNotFound{

        observador.setEstudiante(serEstudiante.buscarPorId(observador.getEstudiante().getId()));
        observador.setProfesor(serProfesor.getById(observador.getProfesor().getId()));

        serObservador.saveObservador(observador);

        return "redirect:/profesor/observador";
    }


    @GetMapping("/profesor/observador/eliminar/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESOR')")
    public String deleteObservador(@PathVariable long id, Model model) throws ProfesorNotFound {
        try {
            Observador observador = serObservador.getObservadorByID(id);
            serObservador.deleteObservador(observador);
        } catch (Exception e) {
            model.addAttribute("deleteError", "No se puede borrar el observador");
        }
        return "redirect:/profesor/observador";
    }

    @GetMapping("/estudiante/observador")
    @PreAuthorize("hasAnyRole('ROLE_ESTUDIANTE')")
    public String getObservadorEstudiante(Model model) throws Exception{

        Estudiante estudiante = serEstudiante.getByUser(serUser.getLoggedUser());

        model.addAttribute("itemNavbar", "observador");
        model.addAttribute("observaciones", serObservador.getObservadorEstudiante(estudiante));
        return "estudiante/observador/index";
    }
}
