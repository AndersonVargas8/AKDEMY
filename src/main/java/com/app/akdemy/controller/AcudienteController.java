package com.app.akdemy.controller;

import com.app.akdemy.Exception.AcudienteNotFound;
import com.app.akdemy.Exception.CustomeFieldValidationException;
import com.app.akdemy.Exception.UsernameOrIdNotFound;
import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.User;
import com.app.akdemy.interfacesServices.IAcudienteService;
import com.app.akdemy.service.EstudianteService;
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

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AcudienteController {

    @Autowired
    private UserService serUser;

    @Autowired
    private IAcudienteService serAcudiente;

    @Autowired
    private EstudianteService serEstudiante;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    // controlador de Acudiente desde coordinador
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

    @GetMapping("/coordinador/acudientes/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String getEditarAcudiente(@PathVariable Long id, Model model) throws AcudienteNotFound {
        Acudiente acudiente = serAcudiente.getById(id);
        model.addAttribute("editarAcudiente", acudiente);
        return "coordinador/acudientes/editarAcudiente.html";
    }

    @PostMapping("/coordinador/editarAcudiente")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String editarAcudiente(@Valid @ModelAttribute("editarAcudiente") Acudiente acudiente,
            BindingResult result,
            Model model) throws Exception {
        Acudiente acudienteFound = serAcudiente.getById(acudiente.getId());
        User user = acudienteFound.getUsuario();

        // Validar acudiente
        if (!acudienteFound.getDocumento().equals(acudiente.getDocumento())) {// Si el documento cambia
            try {
                serAcudiente.validarAcudiente(acudiente);
            } catch (CustomeFieldValidationException e) {
                result.rejectValue(e.getFieldName(), null, e.getMessage());
            } catch (Exception e) {
            }
        }

        // Retornar a la página mostrando los errores
        if (result.getErrorCount() > 0) {
            model.addAttribute("nuevoAcudiente", new Acudiente());
            model.addAttribute("editarAcudiente", acudiente);

            model.addAttribute("errorEditar", 1);
            return "coordinador/acudientes/index";
        }

        // Se pone la contraseña de usuario igual al documento
        String encodePassword = bCryptPasswordEncoder.encode(acudiente.getDocumento());
        user.setPassword(encodePassword);
        user.setConfirmPassword(encodePassword);

        // Se guarda el estudiante
        serAcudiente.saveAcudiente(acudiente);

        return "redirect:/coordinador/acudientes";
    }

    @GetMapping("/coordinador/eliminarAcudiente/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String deleteAcudiente(@PathVariable long id, Model model) throws AcudienteNotFound {
        try {
            Acudiente acudiente = serAcudiente.getById(id);
            serUser.removeRoleAcudiente(acudiente.getUsuario());
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
    @PreAuthorize("hasAnyRole('ROLE_ACUDIENTE', 'ROLE_ADMIN')")
    public String verEstudiantesAcudiente(Model model) throws Exception {
        Acudiente acudiente = serAcudiente.getByUser(serUser.getLoggedUser());

        model.addAttribute("itemNavbar", "estudiantes");
        model.addAttribute("hijos", serEstudiante.getEstudiantesAcudiente(acudiente));
        return "acudiente/estudiantes/index";
    }

    @GetMapping("/acudiente/observaciones")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACUDIENTE')")
    public String observacionesAcudiente(Model model) {
        model.addAttribute("itemNavbar", "observaciones");
        return "acudiente/observaciones/index";
    }

    @GetMapping("/acudiente/horarios")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACUDIENTE')")
    public String horariosAcudiente(Model model) {
        model.addAttribute("itemNavbar", "horarios");
        return "acudiente/horarios/index";
    }

}
