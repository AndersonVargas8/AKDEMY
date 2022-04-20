package com.app.akdemy.controller;

import javax.validation.Valid;

import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.interfacesServices.IEstudianteService;
import com.app.akdemy.repository.EstudianteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class EstudianteController {
    @Autowired
    private EstudianteRepository repProfesor;

    @Autowired
    private IEstudianteService serEstudiante;

    @GetMapping("/coordinador/estudiantes")
    public String index(Model model) {

        model.addAttribute("newEstudiante", new Estudiante());
        model.addAttribute("estudiantes", repProfesor.findAll());
        return "estudiantes/index";
    }

    @PostMapping("/guardarEstudiante")
    public String createProfesor(@Valid Estudiante estudiante, Model model) {
        serEstudiante.guardarEstudiante(estudiante);
        return "redirect:/coordinador/estudiantes";
    }
}
