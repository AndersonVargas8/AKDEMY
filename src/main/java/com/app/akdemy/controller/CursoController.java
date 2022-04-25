package com.app.akdemy.controller;

import javax.validation.Valid;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.entity.Curso;
import com.app.akdemy.interfacesServices.ICursoService;
import com.app.akdemy.interfacesServices.IProfesorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String index(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("cursos", serCurso.getAllCourses());
        model.addAttribute("profesores", serProfesor.getAllProfesors());
        model.addAttribute("itemNavbar","cursos");
        return "coordinador/cursos/index";
    }

    @PostMapping("/savecurso")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String createCurso(@Valid @ModelAttribute("curso")Curso curso, Model model) throws  ProfesorNotFound {

        curso.setProfesor(serProfesor.getById(curso.getProfesor().getId()));
        //profesor.setUsuario(serUser.getUserById(profesor.getUsuario().getId()));
        service.saveCurso(curso);
        return "redirect:/coordinador/cursos";
    }

    @GetMapping("/coordinador/cursos/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String editarCurso(@PathVariable int id, Model model) {

        Curso curso= serCurso.buscarPorId(id);
        model.addAttribute("editarCurso", curso);
        model.addAttribute("cursos", serCurso.getAllCourses());
        model.addAttribute("profesores", serProfesor.getAllProfesors());
        model.addAttribute("itemNavbar","cursos");

        return "coordinador/cursos/editarCursos.html";
    }

    @GetMapping("/coordinador/eliminarCursos/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
	public String deleteCurso(Model model, @PathVariable Long id) {
		try {
			serCurso.deleteCurso(id);
		} catch (Exception e) {
			model.addAttribute("deleteError","The user could not be deleted.");
		}
		return "redirect:/coordinador/cursos";
	}
}
