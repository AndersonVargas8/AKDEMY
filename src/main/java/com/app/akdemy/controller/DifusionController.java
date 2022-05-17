package com.app.akdemy.controller;

import java.util.Date;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.entity.Difusion;
import com.app.akdemy.interfacesServices.ICursoService;
import com.app.akdemy.interfacesServices.IDifusionService;
import com.app.akdemy.interfacesServices.IProfesorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DifusionController {

    @Autowired
    IDifusionService serDifusion;

    @Autowired
    ICursoService serCurso;

    @Autowired
    IProfesorService serProfesor;

    @GetMapping("/coordinador/prueba")
    public String index(Model model) throws ProfesorNotFound {
        Difusion Aviso = new Difusion();
        Aviso.setMessage("Prueba firebase");
        Aviso.setSubject("Interface");
        Aviso.setCurso(serCurso.buscarPorId(1L));
        Aviso.setDate(new Date());
        Aviso.setProfesor(serProfesor.getById(1L));
        serDifusion.saveDifusion(Aviso);
        return "redirect:/coordinador";
    }
    
}
