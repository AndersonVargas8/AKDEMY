package com.app.akdemy.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.GrupoSanguineoRH;
import com.app.akdemy.entity.Role;
import com.app.akdemy.entity.User;
import com.app.akdemy.interfacesServices.IEstudianteService;
import com.app.akdemy.repository.EpsRepository;
import com.app.akdemy.repository.GrupoSanguineoRHRepository;
import com.app.akdemy.repository.TipoDocumentoRepository;
import com.app.akdemy.service.AcudienteService;
import com.app.akdemy.service.RoleService;
import com.app.akdemy.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EstudianteController {

    @Autowired
    private IEstudianteService serEstudiante;

    @Autowired
    RoleService serRole;

    @Autowired
    UserService serUser;

    @Autowired
    AcudienteService serAcudiente;
    
    @Autowired
    TipoDocumentoRepository repTipoDoc;

    @Autowired
    GrupoSanguineoRHRepository repGrupoSanguineoRH;

    @Autowired
    EpsRepository repEps;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/coordinador/estudiantes")
    public String index(Model model) {

        model.addAttribute("nuevoEstudiante", new Estudiante());
        model.addAttribute("tiposDoc", repTipoDoc.findAll());
        model.addAttribute("eps", repEps.findAll());
        model.addAttribute("gsrh", repGrupoSanguineoRH.findAll());
        model.addAttribute("estudiantes", serEstudiante.listarEstudiantes());

        model.addAttribute("itemNavbar", "estudiantes");
        return "coordinador/estudiantes/index";
    }

    @PostMapping("/coordinador/estudiantes")
    public String guardarEstudiante(@Valid @ModelAttribute("estudiante") Estudiante estudiante, BindingResult result,
            Model model) {

        // Se pone la contraseña de usuario igual al documento
        String encodePassword = bCryptPasswordEncoder.encode(estudiante.getDocumento());
        estudiante.getUsuario().setPassword(encodePassword);
        estudiante.getUsuario().setConfirmPassword(encodePassword);
        // Se le asigna el rol estudiante
        Set<Role> roles = new HashSet<>();
        roles.add(serRole.buscarPorNombre("ESTUDIANTE"));
        estudiante.getUsuario().setRoles(roles);

        // Se guarda el user y se le asigna al estudiante
        estudiante.setUsuario(serUser.guardarUsuario(estudiante.getUsuario()));

        // Se agrega un conjunto vacío de acudientes
        Set<Acudiente> acudientes = new HashSet<>();
        estudiante.setAcudientes(acudientes);

        // Se guarda el estudiante
        serEstudiante.guardarEstudiante(estudiante);

        return "redirect:/coordinador/estudiantes";
    }

    @GetMapping("/coordinador/estudiantes/{id}")
    public String getEditarEstudiante(@PathVariable int id, Model model) {
        Estudiante estudiante = serEstudiante.buscarPorId(id);
        model.addAttribute("editarEstudiante", estudiante);
        model.addAttribute("tiposDoc", repTipoDoc.findAll());
        model.addAttribute("estudiantes", serEstudiante.listarEstudiantes());
        model.addAttribute("eps", repEps.findAll());
        model.addAttribute("gsrh", repGrupoSanguineoRH.findAll());
        model.addAttribute("itemNavbar", "estudiantes");
        return "coordinador/estudiantes/editarEstudiante.html";
    }

    @PostMapping("/coordinador/editarEstudiante")
    public String editarEstudiante(@Valid @ModelAttribute("estudiante") Estudiante estudiante, BindingResult result,
            Model model) {
        User user = serEstudiante.buscarPorId(estudiante.getId()).getUsuario();
        if (!user.getUsername().equals(estudiante.getUsuario().getUsername())) {// Si el username cambia
            // Se pone la contraseña de usuario igual al documento
            String encodePassword = bCryptPasswordEncoder.encode(estudiante.getDocumento());
            user.setPassword(encodePassword);
            user.setConfirmPassword(encodePassword);
            //Se modifica el username que tenía por el nuevo
            user.setUsername(estudiante.getUsuario().getUsername());
        }

        // Se guarda el user y se le asigna al estudiante
        estudiante.setUsuario(serUser.guardarUsuario(user));

        Set<Acudiente> acudientes = estudiante.getAcudientes();

        if (acudientes == null) {
            // Se agrega un conjunto vacío de acudientes
            acudientes = new HashSet<>();
            estudiante.setAcudientes(acudientes);
        }
        // Se guarda el estudiante
        serEstudiante.guardarEstudiante(estudiante);

        return "redirect:/coordinador/estudiantes";
    }
}
