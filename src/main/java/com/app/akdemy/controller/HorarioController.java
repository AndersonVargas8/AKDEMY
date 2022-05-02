package com.app.akdemy.controller;

import java.util.Arrays;
import java.util.List;

import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.HorarioCurso;
import com.app.akdemy.service.CursoService;
import com.app.akdemy.service.HorarioService;
import com.app.akdemy.service.MateriaGradoService;
import com.app.akdemy.service.ProfesorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HorarioController {
    @Autowired
    HorarioService serHorario;

    @Autowired
    ProfesorService serProfesor;

    @Autowired
    CursoService serCurso;

    @Autowired
    MateriaGradoService serMateriaGrado;

    @GetMapping("/coordinador/horarios")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String index(Model model){
        model = cargarTablaHorarios(model);
        return "coordinador/horarios/index";
    }

    private Model cargarTablaHorarios(Model model){
        //Obtener los cursos
        List<Curso> cursos = serCurso.getAllCourses();
        model.addAttribute("cursos",cursos);

        //Obtener los horarios
        List<HorarioCurso> horarios = serHorario.obtenerPorCurso(2);

        model.addAttribute("horarios",horarios);
        //Crear las horas del horario
        List<String> horas = Arrays.asList("06","07","08","09","10","11","12","13","14","15","16","17");
        
        model.addAttribute("horas",horas);
        return model;
    }
}
