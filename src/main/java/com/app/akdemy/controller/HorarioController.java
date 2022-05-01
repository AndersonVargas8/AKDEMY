package com.app.akdemy.controller;

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
        return "coordinador/horarios/index";
    }
}
