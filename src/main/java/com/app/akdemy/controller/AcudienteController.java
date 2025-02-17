package com.app.akdemy.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

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

import com.app.akdemy.Exception.AcudienteNotFound;
import com.app.akdemy.Exception.CustomeFieldValidationException;
import com.app.akdemy.Exception.UsernameOrIdNotFound;
import com.app.akdemy.dto.CalificacionesEstDTO;
import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.HorarioCurso;
import com.app.akdemy.entity.Role;
import com.app.akdemy.entity.User;
import com.app.akdemy.interfacesServices.IAcudienteService;
import com.app.akdemy.interfacesServices.IRoleService;
import com.app.akdemy.service.CalificacionesService;
import com.app.akdemy.service.EstudianteService;
import com.app.akdemy.service.HorarioService;
import com.app.akdemy.service.ObservadorService;
import com.app.akdemy.service.UserService;

@Controller
public class AcudienteController {

    @Autowired
    private UserService serUser;

    @Autowired
    private IRoleService serRole;

    @Autowired
    private IAcudienteService serAcudiente;

    @Autowired
    private EstudianteService serEstudiante;

    @Autowired
    private ObservadorService serObservador;

    @Autowired
    private HorarioService serHorario;


    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    // controlador de Acudiente desde coordinador
    @GetMapping("/coordinador/acudientes")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String index(Model model) {

        model.addAttribute("acudiente", new Acudiente());
        model.addAttribute("acudientes", serAcudiente.getAllAcudientes());
        model.addAttribute("users", serUser.getAvailableUsersAcudientes());
        model.addAttribute("itemNavbar", "acudientes");
        return "coordinador/acudientes/index";
    }

    @PostMapping("/saveacudiente")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String createAcudiente(@Valid @ModelAttribute("acudiente") Acudiente acudiente, Model model,
            BindingResult result)
            throws UsernameOrIdNotFound {

        if (acudiente.getUsuario().getId() > 0) {
            acudiente.setUsuario(serUser.getUserById(acudiente.getUsuario().getId()));
        } else {
            // Se pone la contraseña de usuario igual al documento
            String encodePassword = bCryptPasswordEncoder.encode(acudiente.getDocumento());
            acudiente.getUsuario().setPassword(encodePassword);
            acudiente.getUsuario().setConfirmPassword(encodePassword);
            // Se le asigna el rol acudiente
            Set<Role> roles = new HashSet<>();
            roles.add(serRole.buscarPorNombre("ACUDIENTE"));
            acudiente.getUsuario().setRoles(roles);

            // Validar usuario
            try {
                serUser.validarUsuario(acudiente.getUsuario());
            } catch (CustomeFieldValidationException e) {
                result.rejectValue(e.getFieldName(), null, e.getMessage());
            } catch (Exception e) {
            }

            // Retornar a la página mostrando los errores
            if (result.getErrorCount() > 0) {
                model.addAttribute("acudiente", new Acudiente());
                model.addAttribute("acudientes", serAcudiente.getAllAcudientes());
                model.addAttribute("users", serUser.getAvailableUsersAcudientes());
                model.addAttribute("itemNavbar", "acudientes");

                model.addAttribute("errorCrear", 1);
                return "coordinador/acudientes/index";
            }

            // Se guarda el user y se le asigna al acudiente
            User usuario = serUser.guardarUsuario(acudiente.getUsuario());
            acudiente.setUsuario(usuario);
        }
        serAcudiente.saveAcudiente(acudiente);

        if (!serUser.userHasRole(acudiente.getUsuario(), "ACUDIENTE")) {
            serUser.setRoleAcudiente(acudiente.getUsuario());
        }

        return "redirect:/coordinador/acudientes";
    }

    @PostMapping("/cargarAcudientes")
    public String cargarMaterias(Model model){
        model.addAttribute("acudientes", serAcudiente.getAllAcudientes());
        model.addAttribute("users", serUser.getAvailableUsersAcudientes());     
        return "coordinador/acudientes/index::listaAcudientes";
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

    @PostMapping("/coordinador/eliminarAcudiente/{id}")
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

    @GetMapping("/coordinador/acudientes/estudiantes/{idAcudiente}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String estudiantes(@PathVariable long idAcudiente, Model model) throws AcudienteNotFound {
        List<Estudiante> estudianteList = serEstudiante.listarEstudiantes();
        List<Estudiante> estudiantes = new ArrayList<>();
        Acudiente acudiente = serAcudiente.getById(idAcudiente);
        for (Estudiante estudiante : estudianteList) {
            estudiantes.add(estudiante);
        }

        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("acudiente", acudiente);
        model.addAttribute("hijos", serEstudiante.getEstudiantesAcudiente(acudiente));
        model.addAttribute("itemNavbar", "acudientes");
        return "coordinador/acudientes/acudientesEstudiantes";
    }

    @GetMapping("/coordinador/acudientes/estudiantes/listadoEstudiantes/{idAcudiente}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String verTodosEstudiantes(@PathVariable long idAcudiente, Model model) throws AcudienteNotFound {
        List<Estudiante> estudianteList = serEstudiante.listarEstudiantes();
        Acudiente acudiente = serAcudiente.getById(idAcudiente);

        model.addAttribute("estudiantes", estudianteList);
        model.addAttribute("acudiente", acudiente);

        model.addAttribute("itemNavbar", "acudientes");
        return "coordinador/acudientes/listadoEstudiantes";
    }

    @PostMapping("/coordinador/acudientes/estudiantes")
    public String guardarEstudiantes(@ModelAttribute("acudiente") Acudiente acudienteModel) throws AcudienteNotFound {
        Acudiente acudiente = serAcudiente.getById(acudienteModel.getId());
        acudiente.setEstudiantes(acudienteModel.getEstudiantes());
        serAcudiente.saveAcudiente(acudiente);
        return "redirect:/coordinador/acudientes";
    }

    // controlador vista acudiente

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
    public String verObservacionesAcudiente(Model model) throws Exception {
        User user = serUser.getLoggedUser();
        Acudiente acudiente = serAcudiente.getByUser(user);
        List<Estudiante> estudiantes = (List<Estudiante>)serEstudiante.getEstudiantesAcudiente(acudiente);

        if(estudiantes.size() == 1){
            model.addAttribute("unicoEstudiante",estudiantes.get(0).getId());
        }
        model.addAttribute("itemNavbar", "observaciones");
        model.addAttribute("estudiantes", estudiantes);
        /*
         * model.addAttribute("observaciones",
         * serObservador.getObservadorEstudianteByAcudiente(acudiente));
         */

        return "acudiente/observaciones/index";
    }

    @GetMapping("/acudiente/observaciones/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACUDIENTE')")
    public String verObservacionesAcudienteByEstudiante(@PathVariable Long id, Model model) throws Exception {
        Estudiante estudiante = serEstudiante.buscarPorId(id);
        model.addAttribute("observaciones", serObservador.getObservadorEstudiante(estudiante));
        return "acudiente/observaciones/tablaObservaciones";
    }

    @GetMapping("/acudiente/horarios")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACUDIENTE')")
    public String horariosAcudiente(Model model) throws Exception {
        User user = serUser.getLoggedUser();
        Acudiente acudiente = serAcudiente.getByUser(user);
        List<Estudiante> estudiantes = (List<Estudiante>)serEstudiante.getEstudiantesAcudiente(acudiente);

        if(estudiantes.size() == 1){
            model.addAttribute("unicoEstudiante",estudiantes.get(0).getId());
        }
        model.addAttribute("itemNavbar", "horarios");
        model.addAttribute("estudiantes", estudiantes);
        return "acudiente/horarios/index";
    }

    @GetMapping("/acudiente/horarios/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACUDIENTE')")
    public String verHorariosEstudianteAcudiente(@PathVariable Long id, Model model) throws Exception {
        Estudiante estudiante = serEstudiante.buscarPorId(id);
        model.addAttribute("estudiantes", estudiante);
        model = cargarTablaHorarios(id, model);
        return "acudiente/horarios/horarioEstudiante";
    }

    private Model cargarTablaHorarios(@PathVariable Long id, Model model) throws Exception {
        Estudiante estudiante = serEstudiante.buscarPorId(id);
        if (estudiante == null)
            return model;
        model.addAttribute("nombreEstudiante", estudiante.getNombres().concat(" " + estudiante.getApellidos()));

        // Obtener los horarios
        Curso cursoActual = estudiante.getCursoActual();
        if (cursoActual == null)
            return model;

        List<HorarioCurso> horarios = serHorario.obtenerPorCurso(cursoActual.getId());

        model.addAttribute("horarios", horarios);
        // Crear las horas del horario
        List<String> horas = Arrays.asList("06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17");

        model.addAttribute("horas", horas);

        model.addAttribute("nombreCurso", cursoActual.getNombre_Curso());
        return model;
    }

    @GetMapping("/acudiente/calificaciones")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACUDIENTE')")
    public String calificacionesAcudiente(Model model) throws Exception {
        Acudiente acudiente = serAcudiente.getByUser(serUser.getLoggedUser());
        List<Estudiante> estudiantes = (List<Estudiante>)serEstudiante.getEstudiantesAcudiente(acudiente);

        if(estudiantes.size() == 1){
            model.addAttribute("unicoEstudiante",estudiantes.get(0).getId());
        }
        model.addAttribute("itemNavbar", "calificaciones");
        model.addAttribute("estudiantes", estudiantes);
        return "acudiente/calificaciones/index";
    }

    @GetMapping("/acudiente/calificaciones/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACUDIENTE')")
    public String verCalificacionesEstudianteAcudiente(@PathVariable Long id, Model model) throws Exception {
        Estudiante estudiante = serEstudiante.buscarPorId(id);
        CalificacionesEstDTO calificaciones = serEstudiante.getCalificaciones(estudiante);
        model.addAttribute("nombreEstudiante", calificaciones.getNombreEstudiante());
        model.addAttribute("curso", calificaciones.getCurso());
        model.addAttribute("promedioGeneral", calificaciones.getPromedioGeneral());
        model.addAttribute("materias", calificaciones.getMaterias());
        model.addAttribute("id", id);
        return "acudiente/calificaciones/datosEstudiante";
    }

}
