package com.app.akdemy.controller;

import com.app.akdemy.entity.Difusion;
import com.app.akdemy.interfacesServices.IDifusionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DifusionController {

    @Autowired
    IDifusionService serDifusion;

    @GetMapping("/coordinador/prueba")
    public String index(Model model) {
        Difusion Aviso = new Difusion();
        Aviso.setMessage("Prueba firebase");
        serDifusion.saveDifusion(Aviso);
        return "coordinador/estudiantes/index";
    }
    
}
