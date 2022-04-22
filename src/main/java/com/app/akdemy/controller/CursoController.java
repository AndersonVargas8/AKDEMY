package com.app.akdemy.controller;

import javax.validation.Valid;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.Exception.UsernameOrIdNotFound;
import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.interfacesServices.ICursoService;
import com.app.akdemy.interfacesServices.IProfesorService;
import com.app.akdemy.repository.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CursoController {

    @Autowired
    private ICursoService serCurso;

    @Autowired
    private ICursoService service;

    @Autowired
    private IProfesorService serProfesor;


    @GetMapping("/coordinador/cursos")
    public String index(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("cursos", serCurso.getAllCourses());
        model.addAttribute("profesores", serProfesor.getAllProfesors());
        model.addAttribute("itemNavbar","cursos");
        return "coordinador/cursos/index";
    }

    @PostMapping("/savecurso")
    public String createCurso(@Valid @ModelAttribute("curso")Curso curso, Model model) throws  ProfesorNotFound {

        curso.setProfesor(serProfesor.getById(curso.getProfesor().getId()));
        //profesor.setUsuario(serUser.getUserById(profesor.getUsuario().getId()));
        service.saveCurso(curso);
        return "redirect:/coordinador/cursos";
    }
}
