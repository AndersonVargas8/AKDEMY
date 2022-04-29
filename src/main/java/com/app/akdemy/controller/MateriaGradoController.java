package com.app.akdemy.controller;

import javax.validation.Valid;

import com.app.akdemy.Exception.MateriaNotFound;
import com.app.akdemy.entity.MateriaGrado;
import com.app.akdemy.interfacesServices.IMateriaGradoService;
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
public class MateriaGradoController {

    @Autowired
    private IMateriaGradoService serMateriaGrado;

    @Autowired
    private IMateriaGradoService service;

    @Autowired
    private IMateriaService serMateria;


    @GetMapping("/coordinador/materiasGrado")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String index(Model model) {
        model.addAttribute("materiaGrado", new MateriaGrado());
        model.addAttribute("materiasGrado", serMateriaGrado.getAllMateriasGrado());
        model.addAttribute("materias", serMateria.getAllMaterias());
        model.addAttribute("itemNavbar","materias");
        return "coordinador/materiasGrado/index";
    }

    @PostMapping("/savemateriaGrado")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String createMateriaGrado(@Valid @ModelAttribute("materiaGrado")MateriaGrado materiaGrado, Model model) throws  MateriaNotFound {

        materiaGrado.setMateria(serMateria.getById(materiaGrado.getMateria().getId()));
        //profesor.setUsuario(serUser.getUserById(profesor.getUsuario().getId()));
        service.saveMateriaGrado(materiaGrado);
        return "redirect:/coordinador/materiasGrado";
    }

    @GetMapping("/coordinador/materiasGrado/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String editarMateriaGrado(@PathVariable int id, Model model) {

        MateriaGrado materiaGrado = serMateriaGrado.buscarPorId(id);
        model.addAttribute("editarMateriaGrado", materiaGrado);
        model.addAttribute("materiasGrado", serMateriaGrado.getAllMateriasGrado());
        model.addAttribute("materias", serMateria.getAllMaterias());
        model.addAttribute("itemNavbar","materias");

        return "coordinador/materiasGrado/editarMateriasGrado.html";
    }

    @GetMapping("/coordinador/eliminarMateriasGrado/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
	public String deleteMateriaGrado(Model model, @PathVariable Long id) {
		try {
			serMateriaGrado.deleteMateriaGrado(id);
		} catch (Exception e) {
			model.addAttribute("deleteError","The school subject could not be deleted.");
		}
		return "redirect:/coordinador/materiasGrado";
	}
    
}
