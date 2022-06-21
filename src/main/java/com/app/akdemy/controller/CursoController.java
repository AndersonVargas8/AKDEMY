package com.app.akdemy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.interfacesServices.ICursoService;
import com.app.akdemy.interfacesServices.IEstudianteService;
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

    @Autowired
    private IEstudianteService serEstudiante;

    @GetMapping("/coordinador/cursos")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String index(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("cursos", serCurso.getAllCourses());
        model.addAttribute("profesores", serProfesor.getAllProfesors());
        model.addAttribute("itemNavbar", "cursos");
        return "coordinador/cursos/index";
    }

    @PostMapping("/savecurso")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String createCurso(@Valid @ModelAttribute("curso") Curso curso, Model model) throws ProfesorNotFound {

        curso.setProfesor(serProfesor.getById(curso.getProfesor().getId()));
        // profesor.setUsuario(serUser.getUserById(profesor.getUsuario().getId()));
        service.saveCurso(curso);
        return "redirect:/coordinador/cursos";
    }

    @PostMapping("/cargarCursos")
    public String cargarMaterias(Model model){
        model.addAttribute("cursos", serCurso.getAllCourses());
        return "coordinador/cursos/index::listaCursos";
    }

    @GetMapping("/coordinador/cursos/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String editarCurso(@PathVariable int id, Model model) {

        Curso curso = serCurso.buscarPorId(id);
        model.addAttribute("editarCurso", curso);
        model.addAttribute("cursos", serCurso.getAllCourses());
        model.addAttribute("profesores", serProfesor.getAllProfesors());
        model.addAttribute("itemNavbar", "cursos");

        return "coordinador/cursos/editarCursos.html";
    }

    @PostMapping("/coordinador/eliminarCursos/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String deleteCurso(Model model, @PathVariable Long id) {
        try {
            serCurso.deleteCurso(id);
        } catch (Exception e) {
            model.addAttribute("deleteError", "The course could not be deleted.");
        }
        return "redirect:/coordinador/cursos";
    }

    @GetMapping("/coordinador/cursos/estudiantes/{idCurso}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String estudiantes(@PathVariable long idCurso,Model model) {
        List<Estudiante> estudianteList = serEstudiante.listarEstudiantes();
        List<Estudiante> estudiantes = new ArrayList<>();
        Curso curso = serCurso.buscarPorId(idCurso);
        for(Estudiante estudiante: estudianteList){
            Curso cursoActual = estudiante.getCursoActual();
            if(cursoActual == null || cursoActual.equals(curso)){
                estudiantes.add(estudiante);
            }
        }

        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("curso",curso);
        return "coordinador/cursos/cursosEstudiantes";
    }

    @PostMapping("/coordinador/cursos/estudiantes")
    public String guardarEstudiantes(@ModelAttribute("curso") Curso cursoAct) {
        Curso curso = serCurso.buscarPorId(cursoAct.getId());
        curso.setEstudiantes(cursoAct.getEstudiantes());
        serCurso.saveCurso(curso);
        return "redirect:/coordinador/cursos";
    }
    // controlador cursos para el profesor

}
