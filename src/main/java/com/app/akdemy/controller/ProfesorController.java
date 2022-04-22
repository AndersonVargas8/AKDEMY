package com.app.akdemy.controller;

import javax.validation.Valid;

import com.app.akdemy.Exception.UsernameOrIdNotFound;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.entity.User;
import com.app.akdemy.interfacesServices.IProfesorService;
import com.app.akdemy.repository.ProfesorRepository;
import com.app.akdemy.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfesorController {

    @Autowired
    private IProfesorService service;

    @Autowired
    private ProfesorRepository repProfesor;

    @Autowired
    private UserService serUser;

    
    @GetMapping("/coordinador/profesores")
    public String index(Model model) {

        model.addAttribute("profesor", new Profesor());
        model.addAttribute("profesores", repProfesor.findAll());
        model.addAttribute("users", serUser.getAllUsers());
        model.addAttribute("itemNavbar","profesores");
        return "coordinador/profesores/index";
    }

    @PostMapping("/saveprofesor")
    public String createProfesor(@Valid @ModelAttribute("profesor")Profesor profesor, Model model) throws UsernameOrIdNotFound {

        profesor.setUsuario(serUser.getUserById(profesor.getUsuario().getId()));
        service.saveProfesor(profesor);
        return "redirect:/coordinador/profesores";
    }
}
