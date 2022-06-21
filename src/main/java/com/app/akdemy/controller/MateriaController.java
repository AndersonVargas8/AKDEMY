package com.app.akdemy.controller;

import javax.validation.Valid;

import com.app.akdemy.entity.Materia;
import com.app.akdemy.interfacesServices.IMateriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MateriaController {

    @Autowired
    private IMateriaService serMateria;

    @Autowired
    private IMateriaService service;

    @GetMapping("/coordinador/materias")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String index(Model model) {
        model.addAttribute("materia", new Materia());
        model.addAttribute("materias", serMateria.getAllMaterias());
        model.addAttribute("itemNavbar","materias");
        return "coordinador/materias/index";
    }

    @PostMapping("/savemateria")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String createMateria(@Valid @ModelAttribute("materia")Materia materia, Model model){

        service.saveMateria(materia);
        return "redirect:/coordinador/materias";

    }

    @PostMapping("/cargarMaterias")
    public String cargarMaterias(Model model){
        model.addAttribute("materias", serMateria.getAllMaterias());
        return "coordinador/materias/index::listaMaterias";
    }

    @GetMapping("/coordinador/materias/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String editarMateria(@PathVariable int id, Model model) {

        Materia materia = serMateria.buscarPorId(id);
        model.addAttribute("editarMateria", materia);
        model.addAttribute("materias", serMateria.getAllMaterias());
        model.addAttribute("itemNavbar","materias");

        return "coordinador/materias/editarMaterias.html";
    }

    @PostMapping("/coordinador/eliminarMaterias/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
	public String deleteMateria(Model model, @PathVariable Long id) {
		try {
			serMateria.deleteMateria(id);
		} catch (Exception e) {
			model.addAttribute("deleteError","The school subject could not be deleted.");
		}
		return "redirect:/coordinador/materias";
	}
    
}
