package com.app.akdemy.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoordinadorController {

    @GetMapping("/coordinador")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String inicioCoordinador(Model model) {
        model.addAttribute("itemNavbar", "inicio");
        return "coordinador/menu";
    }

    @GetMapping("/acudiente")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACUDIENTE')")
    public String inicioAcudiente(Model model) {
        model.addAttribute("itemNavbar", "inicio");
        return "acudiente/index";
    }

}
