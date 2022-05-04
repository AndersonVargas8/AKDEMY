package com.app.akdemy.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import com.app.akdemy.Exception.CustomeFieldValidationException;
import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Estudiante;
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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String index(Model model) {

        model.addAttribute("nuevoEstudiante", new Estudiante());
        model = addAttributtes(model);
        return "coordinador/estudiantes/index";
    }

    @PostMapping("/coordinador/estudiantes")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String guardarEstudiante(@Valid @ModelAttribute("nuevoEstudiante") Estudiante estudiante,
            BindingResult result,
            Model model) {

        // Se pone la contraseña de usuario igual al documento
        String encodePassword = bCryptPasswordEncoder.encode(estudiante.getDocumento());
        estudiante.getUsuario().setPassword(encodePassword);
        estudiante.getUsuario().setConfirmPassword(encodePassword);
        // Se le asigna el rol estudiante
        Set<Role> roles = new HashSet<>();
        roles.add(serRole.buscarPorNombre("ESTUDIANTE"));
        estudiante.getUsuario().setRoles(roles);

        // Validar estudiante
        try {
            serEstudiante.validarEstudiante(estudiante);
        } catch (CustomeFieldValidationException e) {
            result.rejectValue(e.getFieldName(), null, e.getMessage());
        } catch (Exception e) {
        }

        // Validar usuario
        try {
            serUser.validarUsuario(estudiante.getUsuario());
        } catch (CustomeFieldValidationException e) {
            result.rejectValue(e.getFieldName(), null, e.getMessage());
        } catch (Exception e) {
        }

        // Retornar a la página mostrando los errores
        if (result.getErrorCount() > 0) {
            model.addAttribute("nuevoEstudiante", estudiante);
            model = addAttributtes(model);

            model.addAttribute("errorCrear", 1);
            return "coordinador/estudiantes/index";
        }

        // Se guarda el user y se le asigna al estudiante
        User usuario = serUser.guardarUsuario(estudiante.getUsuario());
        estudiante.setUsuario(usuario);

        // Se agrega un conjunto vacío de acudientes
        Set<Acudiente> acudientes = new HashSet<>();
        estudiante.setAcudientes(acudientes);

        // Se guarda el estudiante
        serEstudiante.guardarEstudiante(estudiante);

        return "redirect:/coordinador/estudiantes";
    }

    @GetMapping("/coordinador/estudiantes/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String getEditarEstudiante(@PathVariable int id, Model model) {
        Estudiante estudiante = serEstudiante.buscarPorId(id);
        model.addAttribute("editarEstudiante", estudiante);
        model = addAttributtes(model);
        return "coordinador/estudiantes/editarEstudiante.html";
    }

    @PostMapping("/coordinador/editarEstudiante")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String editarEstudiante(@Valid @ModelAttribute("editarEstudiante") Estudiante estudiante,
            BindingResult result,
            Model model) throws Exception {
        Estudiante estudianteFound = serEstudiante.buscarPorId(estudiante.getId());
        User user = estudianteFound.getUsuario();

        // Validar estudiante
        if (!estudianteFound.getDocumento().equals(estudiante.getDocumento())) {// Si el documento cambia
            try {
                serEstudiante.validarEstudiante(estudiante);
            } catch (CustomeFieldValidationException e) {
                result.rejectValue(e.getFieldName(), null, e.getMessage());
            } catch (Exception e) {
            }
        }

        if (!user.getUsername().equals(estudiante.getUsuario().getUsername())) {// Si el username cambia
            // Validar usuario
            try {
                serUser.validarUsuario(estudiante.getUsuario());
            } catch (CustomeFieldValidationException e) {
                result.rejectValue(e.getFieldName(), null, e.getMessage());
            } catch (Exception e) {
            }
        }

        // Retornar a la página mostrando los errores
        if (result.getErrorCount() > 0) {
            model.addAttribute("nuevoEstudiante", new Estudiante());
            model.addAttribute("editarEstudiante", estudiante);
            model = addAttributtes(model);

            model.addAttribute("errorEditar", 1);
            return "coordinador/estudiantes/index";
        }
        if (!user.getUsername().equals(estudiante.getUsuario().getUsername())) {// Si el username cambia
            // Se pone la contraseña de usuario igual al documento
            String encodePassword = bCryptPasswordEncoder.encode(estudiante.getDocumento());
            user.setPassword(encodePassword);
            user.setConfirmPassword(encodePassword);
            // Se modifica el username que tenía por el nuevo
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

    private Model addAttributtes(Model model) {
        model.addAttribute("tiposDoc", repTipoDoc.findAll());
        model.addAttribute("eps", repEps.findAll());
        model.addAttribute("gsrh", repGrupoSanguineoRH.findAll());
        model.addAttribute("estudiantes", serEstudiante.listarEstudiantes());

        model.addAttribute("itemNavbar", "estudiantes");
        return model;
    }

    @GetMapping("/coordinador/eliminarEstudiante/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String eliminarEstudiante(@PathVariable int id, Model model) {
        Estudiante estudiante = serEstudiante.buscarPorId(id);
        serEstudiante.eliminarEstudiante(estudiante);

        model = addAttributtes(model);
        return "coordinador/estudiantes/listaEstudiantes";
    }

    // controlador vista estudiante
    @GetMapping("/estudiante")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ESTUDIANTE')")
    public String inicioCoordinador(Model model) {
        model.addAttribute("itemNavbar", "inicio");
        return "estudiante/index";
    }
}
