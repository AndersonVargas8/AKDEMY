package com.app.akdemy.controller;

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

import com.app.akdemy.Exception.CustomeFieldValidationException;
import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.Exception.UsernameOrIdNotFound;
import com.app.akdemy.dto.CalificacionDTO;
import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.HorarioCurso;
import com.app.akdemy.entity.MateriaGrado;
import com.app.akdemy.entity.Observador;
import com.app.akdemy.entity.Periodo;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.entity.Role;
import com.app.akdemy.entity.User;
import com.app.akdemy.interfacesServices.ICalificacionesService;
import com.app.akdemy.interfacesServices.ICursoService;
import com.app.akdemy.interfacesServices.IEstudianteService;
import com.app.akdemy.interfacesServices.IHorarioService;
import com.app.akdemy.interfacesServices.IMateriaGradoService;
import com.app.akdemy.interfacesServices.IProfesorService;
import com.app.akdemy.interfacesServices.IRoleService;
import com.app.akdemy.service.UserService;

@Controller
public class ProfesorController {

    @Autowired
    private IProfesorService serProfesor;

    @Autowired
    private UserService serUser;

    @Autowired
    private IRoleService serRole;

    @Autowired
    private IHorarioService serHorario;

    @Autowired
    private ICursoService serCurso;

    @Autowired
    private IMateriaGradoService serMateriaGrado;

    @Autowired
    private ICalificacionesService serCalificaciones;

    @Autowired
    private IEstudianteService serEstudiante;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    // controlador de profesor desde coordinador
    @GetMapping("/coordinador/profesores")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String index(Model model) {

        model.addAttribute("profesor", new Profesor());
        model.addAttribute("profesores", serProfesor.getAllProfesors());
        model.addAttribute("users", serUser.getAvailableUsersProfesores());
        model.addAttribute("itemNavbar", "profesores");
        return "coordinador/profesores/index";
    }

    @PostMapping("/saveprofesor")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String createProfesor(@Valid @ModelAttribute("profesor") Profesor profesor, Model model,
            BindingResult result)
            throws UsernameOrIdNotFound {

        if (profesor.getUsuario().getId() > 0) {
            profesor.setUsuario(serUser.getUserById(profesor.getUsuario().getId()));
        } else {
            // Se pone la contraseña de usuario igual al documento
            String encodePassword = bCryptPasswordEncoder.encode(profesor.getDocumento());
            profesor.getUsuario().setPassword(encodePassword);
            profesor.getUsuario().setConfirmPassword(encodePassword);
            // Se le asigna el rol profesor
            Set<Role> roles = new HashSet<>();
            roles.add(serRole.buscarPorNombre("PROFESOR"));
            profesor.getUsuario().setRoles(roles);

            // Validar usuario
            try {
                serUser.validarUsuario(profesor.getUsuario());
            } catch (CustomeFieldValidationException e) {
                result.rejectValue(e.getFieldName(), null, e.getMessage());
            } catch (Exception e) {
            }

            // Retornar a la página mostrando los errores
            if (result.getErrorCount() > 0) {
                model.addAttribute("profesor", profesor);
                model.addAttribute("profesores", serProfesor.getAllProfesors());
                model.addAttribute("users", serUser.getAvailableUsersProfesores());
                model.addAttribute("itemNavbar", "profesores");

                model.addAttribute("errorCrear", 1);
                return "coordinador/profesores/index";
            }

            // Se guarda el user y se le asigna al profesor
            User usuario = serUser.guardarUsuario(profesor.getUsuario());
            profesor.setUsuario(usuario);
        }

        serProfesor.saveProfesor(profesor);

        if (!serUser.userHasRole(profesor.getUsuario(), "PROFESOR")) {
            serUser.setRoleProfesor(profesor.getUsuario());
        }
        return "redirect:/coordinador/profesores";
    }

    @GetMapping("/coordinador/profesores/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String getEditarProfesor(@PathVariable long id, Model model) throws ProfesorNotFound {

        Profesor profesor = serProfesor.getById(id);

        model.addAttribute("editarProfesor", profesor);
        model.addAttribute("profesores", serProfesor.getAllProfesors());
        model.addAttribute("users", serUser.getAvailableUsersProfesores());
        model.addAttribute("itemNavbar", "profesores");

        return "coordinador/profesores/editarProfesor.html";
    }

    @GetMapping("/coordinador/eliminarProfesor/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_COORDINADOR')")
    public String deleteProfesor(@PathVariable long id, Model model) throws ProfesorNotFound {
        try {
            Profesor profesor = serProfesor.getById(id);
            serUser.removeRoleProfesor(profesor.getUsuario());
            serProfesor.deleteProfesor(profesor);
        } catch (Exception e) {
            model.addAttribute("deleteError", "No se puede borrar el usuario");
        }
        return "redirect:/coordinador/profesores";
    }

    // controlador profesor-----------------------------------------------

    @GetMapping("/profesor")
    @PreAuthorize("hasAnyRole('ROLE_PROFESOR', 'ROLE_ADMIN')")
    public String inicioCoordinador(Model model) {
        model.addAttribute("itemNavbar", "inicio");
        return "profesor/index";
    }

    @GetMapping("/profesor/horario")
    @PreAuthorize("hasAnyRole('ROLE_PROFESOR', 'ROLE_ADMIN')")
    public String verHorarioProfesor(Model model) throws Exception {

        model.addAttribute("itemNavbar", "horario");
        model = cargarTablaHorarios(model);
        return "profesor/horario/index";
    }

    private Model cargarTablaHorarios(Model model) throws Exception {
        User user = serUser.getLoggedUser();
        Profesor profesor;
        try {
            profesor = serProfesor.getByUser(user);
        } catch (Exception e) {
            return model;
        }
        // Obtener los horarios
        List<HorarioCurso> horarios = serHorario.obtenerPorProfesor(profesor);

        model.addAttribute("horarios", horarios);
        // Crear las horas del horario
        List<String> horas = Arrays.asList("06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17");

        model.addAttribute("horas", horas);

        model.addAttribute("nombreProfesor", profesor.getNombres().concat(" " + profesor.getApellidos()));
        return model;
    }

    @GetMapping("/profesor/observador")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESOR')")
    public String verObservacionProfesor(Model model) throws Exception {
        Profesor currentProfesor = serProfesor.getByUser(serUser.getLoggedUser());

        Observador observador = new Observador();
        observador.setEstudiante(new Estudiante());
        observador.setProfesor(currentProfesor);

        model.addAttribute("itemNavbar", "observador");
        model.addAttribute("courses", serCurso.getCoursesObservadorbyProfesor(currentProfesor));
        // model.addAttribute("observacion", observador);
        return "profesor/observador/index";
    }

    @GetMapping("/profesor/cursos")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_PROFESOR')")
    public String verCursosProfesor(Model model) throws Exception {
        User user = serUser.getLoggedUser();
        Profesor profesor = serProfesor.getByUser(user);

        model.addAttribute("itemNavBar", "cursos");
        model.addAttribute("cursos", serCurso.getCursosProfesor(profesor));

        return "profesor/cursos/index";
    }

    @GetMapping("/profesor/calificaciones")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESOR')")
    public String calificaciones(Model model) throws ProfesorNotFound, Exception {
        Profesor profesor = serProfesor.getByUser(serUser.getLoggedUser());
        List<Curso> cursos = serCurso.getCursosProfesor(profesor);
        List<Periodo> periodos = serCalificaciones.getAllPeriodos();

        if(cursos.size() == 1){
            model.addAttribute("unicoCurso",cursos.get(0).getId());
        }
        model.addAttribute("cursos", cursos);
        model.addAttribute("calificaciones", new CalificacionDTO());
        model.addAttribute("periodos", periodos);
        model.addAttribute("itemNavbar", "calificaciones");
        return "profesor/calificaciones/index";
    }

    @GetMapping("/profesor/calificaciones/estudiantesCalificaciones/{idCurso}/{idMateria}/{idPeriodo}")
    public String getEstudiantesCurso(Model model, @PathVariable int idCurso, @PathVariable int idMateria,
            @PathVariable int idPeriodo) throws ProfesorNotFound, Exception {
        long idProfesor = serProfesor.getByUser(serUser.getLoggedUser()).getId();
        CalificacionDTO calificaciones = serCalificaciones.findEstudiantesCalificaciones(idCurso, idMateria, idPeriodo,
                idProfesor);
        model.addAttribute("calificaciones", calificaciones);
        return "profesor/calificaciones/listaEstudiantes";
    }

    @GetMapping("/profesor/calificaciones/materiasCurso/{idCurso}")
    public String getMateriasCurso(Model model, @PathVariable int idCurso) throws ProfesorNotFound, Exception {
        List<MateriaGrado> materias = serMateriaGrado.getByCursoAndProfesor(idCurso,
                serProfesor.getByUser(serUser.getLoggedUser()));
        if(materias.size() == 1){
            model.addAttribute("unicaMateria",materias.get(0).getId());
        }
        model.addAttribute("materias", materias);
        return "profesor/calificaciones/selectMaterias";
    }

    @PostMapping("/profesor/calificaciones")
    public String guardarCalificaciones(@ModelAttribute CalificacionDTO calificaciones)
            throws ProfesorNotFound, Exception {
        serProfesor.guardarCalificaciones(calificaciones, false);
        return "redirect:/profesor/calificaciones";
    }

    @PostMapping("/profesor/calificaciones/cerrar")
    public String cerrarCalificaciones(@ModelAttribute CalificacionDTO calificaciones)
            throws ProfesorNotFound, Exception {
        serProfesor.guardarCalificaciones(calificaciones, true);
        return "redirect:/profesor/calificaciones";
    }

    @GetMapping("/acudiente/comunicaciones/profesores/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESOR')")
    public String getEstudiantesChat(@PathVariable Long id, Model model) {
        Curso currentCurso = serEstudiante.buscarPorId(id).getCursoActual();
        model.addAttribute("profesores", serProfesor.getProfesoresCurso(currentCurso));
        return "acudiente/comunicaciones/chats/selectProfesores";
    }

}
