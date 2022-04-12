package com.app.akdemy.controller;

import java.util.ArrayList;
import java.util.List;

import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.repository.EstudianteRepository;
import com.app.akdemy.service.CursoService;
import com.app.akdemy.service.EstudianteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EstudianteController {
    @Autowired
    EstudianteService serEstudiante;
    
    @Autowired
    CursoService serCurso;

    @Autowired
    EstudianteRepository repEstudiante;

    @GetMapping("/estudiantes")
    public String index(Model model) {

        List<String> tiposDocumento = new ArrayList<>();
        tiposDocumento.add("Registro civil");
        tiposDocumento.add("Tarjeta de identidad");
        tiposDocumento.add("Cédula de ciudadanía");
        tiposDocumento.add("Cédula de extranjería");
        tiposDocumento.add("Pasaporte");

        
        model.addAttribute("nuevoEstudiante",new Estudiante());
        model.addAttribute("tiposDocumento",tiposDocumento);
        model.addAttribute("cursos",serCurso.obtenerTodos());
        model.addAttribute("estudiantes601", repEstudiante.findAll());
        return "estudiantes/index";
    }

    @PostMapping("/estudiantes")
    public String createEstudiante(@ModelAttribute("estudiante") Estudiante estudiante,BindingResult result, Model model) {
        serEstudiante.guardarEstudiante(estudiante);

        List<String> tiposDocumento = new ArrayList<>();
        tiposDocumento.add("Registro civil");
        tiposDocumento.add("Tarjeta de identidad");
        tiposDocumento.add("Cédula de ciudadanía");
        tiposDocumento.add("Cédula de extranjería");
        tiposDocumento.add("Pasaporte");
        model.addAttribute("nuevoEstudiante",new Estudiante());
        model.addAttribute("tiposDocumento",tiposDocumento);
        model.addAttribute("cursos",serCurso.obtenerTodos());
        model.addAttribute("estudiantes601", serEstudiante.buscarPorCurso(601));
        return "estudiantes/index";
    }
}
