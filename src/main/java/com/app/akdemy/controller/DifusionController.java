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
        Aviso.setMessage("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        Aviso.setSubject("Interface");
        Aviso.setCurso(serCurso.buscarPorId(1L));
        Aviso.setDate(new Date());
        Aviso.setProfesor(serProfesor.getById(1L));
        serDifusion.saveDifusion(Aviso);
        model.addAttribute("difusiones", serDifusion.getDifusionesCurso(serCurso.buscarPorId(1L)));

        return "acudiente/comunicaciones/index";
    }
    
}
