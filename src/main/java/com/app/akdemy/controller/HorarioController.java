package com.app.akdemy.controller;

import java.sql.Time;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PostMapping;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.HorarioCurso;
import com.app.akdemy.entity.MateriaGrado;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.service.CursoService;
import com.app.akdemy.service.HorarioService;
import com.app.akdemy.service.MateriaGradoService;
import com.app.akdemy.service.ProfesorService;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String index(Model model) {
        // Obtener los cursos
        List<Curso> cursos = serCurso.getAllCourses();
        model.addAttribute("cursos", cursos);
        // Cargar los horarios
        if (cursos != null && cursos.size() > 0)
            model = cargarTablaHorarios(model, cursos.get(0).getId());

        model.addAttribute("itemNavbar", "horarios");
        return "coordinador/horarios/index";
    }

    
    @PostMapping("/coordinador/consultaHorario/{idCurso}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String consultaHorario(Model model, @PathVariable int idCurso, HttpServletResponse servletResponse) {
        try{
            model = cargarTablaHorarios(model, idCurso);
            servletResponse.setStatus(HttpServletResponse.SC_OK);
        }catch(Exception e){
            servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return "coordinador/horarios/horario";
    }

    private Model cargarTablaHorarios(Model model, long idCurso) {

        // Obtener los horarios
        List<HorarioCurso> horarios = serHorario.obtenerPorCurso(idCurso);

        model.addAttribute("horarios", horarios);
        // Crear las horas del horario
        List<String> horas = Arrays.asList("06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17");

        model.addAttribute("horas", horas);

        // Obtener materias
        List<MateriaGrado> materias = serMateriaGrado.getAllMateriasGrado();
        model.addAttribute("materias", materias);

        // Obtener profesores
        List<Profesor> profesores = serProfesor.getAllProfesors();
        model.addAttribute("profesores", profesores);
        model.addAttribute("nombreCurso", serCurso.buscarPorId(idCurso).getNombre_Curso());
        return model;
    }

    @GetMapping("/coordinador/eliminarHorario/{idHorario}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String eliminarHorario(Model model, @PathVariable int idHorario) {
        HorarioCurso horario = serHorario.obtenerPorId(idHorario);
        serHorario.eliminarHorario(idHorario);
        model = cargarTablaHorarios(model, horario.getCurso().getId());

        return "coordinador/horarios/horario";
    }

    @GetMapping("/coordinador/agregarHorario/{idCurso}/{dia}/{horaInicio}/{horaFin}/{idMateria}/{idProfesor}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String agregarHorario(Model model, @PathVariable int idCurso, @PathVariable String dia,
            @PathVariable String horaInicio, @PathVariable String horaFin, @PathVariable int idMateria,
            @PathVariable int idProfesor) {
        HorarioCurso horario = new HorarioCurso();
        horario.setId(0);
        horario.setCurso(serCurso.buscarPorId(idCurso));
        horario.setDia(dia);
        horario.setHoraInicio(Time.valueOf(horaInicio));
        horario.setHoraFin(Time.valueOf(horaFin));
        horario.setMateria(serMateriaGrado.buscarPorId(idMateria));
        try {
            horario.setProfesor(serProfesor.getById(new Long(idProfesor)));
        } catch (ProfesorNotFound e) {
        }

        serHorario.guardarHorario(horario);

        model = cargarTablaHorarios(model, idCurso);
        return "coordinador/horarios/horario";
    }

}
