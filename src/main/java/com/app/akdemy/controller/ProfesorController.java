package com.app.akdemy.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import com.app.akdemy.entity.Profesor;
import com.app.akdemy.interfacesServices.IProfesorService;
import com.app.akdemy.repository.ProfesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfesorController {

    @Autowired
    private IProfesorService service;

    @Autowired
    private ProfesorRepository repProfesor;


    @GetMapping("/profesores")
    public String index(Model model) {

        model.addAttribute("nuevoProfesor", new Profesor());
        model.addAttribute("profesores", repProfesor.findAll());
        return "profesores/index";
    }

    @PostMapping("/saveprofesor")
    public String createProfesor(@Valid Profesor profesor, Model model){
        service.saveProfesor(profesor);
        return "redirect:/profesores";
    }
}
