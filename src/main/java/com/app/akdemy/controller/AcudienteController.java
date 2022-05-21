package com.app.akdemy.controller;

import com.app.akdemy.Exception.AcudienteNotFound;
import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.Exception.UsernameOrIdNotFound;
import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.interfacesServices.IAcudienteService;
import com.app.akdemy.interfacesServices.IProfesorService;
import com.app.akdemy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AcudienteController {

    @Autowired
    private UserService serUser;

    @Autowired
    private IAcudienteService serAcudiente;

    // controlador de profesor desde coordinador
    @GetMapping("/coordinador/acudientes")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String index(Model model) {

        model.addAttribute("acudiente", new Acudiente());
        model.addAttribute("acudientes", serAcudiente.getAllAcudientes());
        model.addAttribute("users", serUser.getAvailableUsersProfesores());
        model.addAttribute("itemNavbar", "acudientes");
        return "coordinador/acudientes/index";
    }

    @PostMapping("/saveacudiente")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String createAcudiente(@Valid @ModelAttribute("acudiente") Acudiente acudiente, Model model)
            throws UsernameOrIdNotFound {

        acudiente.setUsuario(serUser.getUserById(acudiente.getUsuario().getId()));
        serAcudiente.saveAcudiente(acudiente);
        serUser.setRoleAcudiente(acudiente.getUsuario());
        return "redirect:/coordinador/acudientes";
    }

    @GetMapping("/coordinador/acudiente/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String getEditarProfesor(@PathVariable long id, Model model) throws AcudienteNotFound {
        Acudiente acudiente = serAcudiente.getById(id);
        model.addAttribute("editarAcudiente", acudiente);
        model.addAttribute("acudientes", serAcudiente.getAllAcudientes());
        model.addAttribute("users", serUser.getAvailableUsersAcudientes());
        model.addAttribute("itemNavbar", "acudientes");
        return "coordinador/acudientes/editarAcudiente.html";
    }

    @GetMapping("/coordinador/eliminarAcudiente/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String deleteAcudiente(@PathVariable long id, Model model) throws AcudienteNotFound {
        try {
            Acudiente acudiente = serAcudiente.getById(id);
            serUser.removeRoleProfesor(acudiente.getUsuario());
            serAcudiente.deleteAcudiente(acudiente);
        } catch (Exception e) {
            model.addAttribute("deleteError", "No se puede borrar el usuario");
        }
        return "redirect:/coordinador/acudientes";
    }

    @GetMapping("/acudiente")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACUDIENTE')")
    public String inicioAcudiente(Model model) {
        model.addAttribute("itemNavbar", "inicio");
        return "acudiente/index";
    }

    @GetMapping("/acudiente/estudiantes")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACUDIENTE')")
    public String estudiantesAcudiente(Model model) {
        model.addAttribute("itemNavbar", "estudiantes");
        return "acudiente/estudiantes/index";
    }

    @GetMapping("/acudiente/observaciones")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACUDIENTE')")
    public String observacionesAcudiente(Model model) {
        model.addAttribute("itemNavbar", "observaciones");
        return "acudiente/observaciones/index";
    }

}
