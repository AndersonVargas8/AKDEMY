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
import org.springframework.web.bind.annotation.PathVariable;

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
        //Obtener los cursos
        List<Curso> cursos = serCurso.getAllCourses();
        model.addAttribute("cursos",cursos);
        //Cargar los horarios
        model = cargarTablaHorarios(model, cursos.get(0).getId());
        return "coordinador/horarios/index";
    }

    @GetMapping("/coordinador/consultaHorario/{idCurso}")
    public String consultaHorario(Model model, @PathVariable int idCurso){
        model = cargarTablaHorarios(model, idCurso);
        return "coordinador/horarios/horario";
    }

    private Model cargarTablaHorarios(Model model, long idCurso){

        //Obtener los horarios
        List<HorarioCurso> horarios = serHorario.obtenerPorCurso(idCurso);

        model.addAttribute("horarios",horarios);
        //Crear las horas del horario
        List<String> horas = Arrays.asList("06","07","08","09","10","11","12","13","14","15","16","17");
        
        model.addAttribute("horas",horas);
        return model;
    }

    @GetMapping("/coordinador/eliminarHorario/{idHorario}")
    public String eliminarHorario(Model model, @PathVariable int idHorario){
        HorarioCurso horario = serHorario.obtenerPorId(idHorario);
        serHorario.eliminarHorario(idHorario);
        model = cargarTablaHorarios(model, horario.getCurso().getId());
        
        return "coordinador/horarios/horario";
    }
}
