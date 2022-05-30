package com.app.akdemy.controller;

import java.util.Date;

import javax.validation.Valid;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Chat;
import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Difusion;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.interfacesServices.IAcudienteService;
import com.app.akdemy.interfacesServices.IChatService;
import com.app.akdemy.interfacesServices.ICursoService;
import com.app.akdemy.interfacesServices.IDifusionService;
import com.app.akdemy.interfacesServices.IEstudianteService;
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

@Controller
public class DifusionController {

    @Autowired
    IDifusionService serDifusion;

    @Autowired
    ICursoService serCurso;

    @Autowired
    IProfesorService serProfesor;

    @Autowired
    UserService serUser;

    @Autowired
    IChatService serChat;

    @Autowired
    IAcudienteService serAcudiente;

    @Autowired
    IEstudianteService serEstudiante;

    // Vista de pantalla de comunicaciones

    @GetMapping("/acudiente/comunicaciones")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACUDIENTE')")
    public String index(Model model) throws Exception {

        Acudiente currentAcudiente = serAcudiente.getByUser(serUser.getLoggedUser());

        model.addAttribute("acudiente", currentAcudiente);
        model.addAttribute("estudiantes", serEstudiante.getEstudiantesAcudiente(currentAcudiente));
        model.addAttribute("chats", serChat.getChats(currentAcudiente));
        model.addAttribute("chat", new Chat());
        
        return "acudiente/comunicaciones/index";
    }

    @GetMapping("/profesor/comunicaciones")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESOR')")
    public String comunicacionesProfesor(Model model) throws ProfesorNotFound, Exception{

        Profesor currentProfesor = serProfesor.getByUser(serUser.getLoggedUser());

        model.addAttribute("profesor", currentProfesor);
        model.addAttribute("courses", serCurso.getCoursesObservadorbyProfesor(currentProfesor));
        model.addAttribute("chats", serChat.getChats(currentProfesor));
        model.addAttribute("chat", new Chat());

        return "profesor/comunicaciones/index";
    }

    //Buscar difusiones

    @GetMapping("/profesor/comunicaciones/difusiones/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESOR')")
    public String getObservacionesCurso(@PathVariable Long id, Model model) throws ProfesorNotFound, Exception{
        
        Profesor currentProfesor = serProfesor.getByUser(serUser.getLoggedUser());
        Curso curso = serCurso.buscarPorId(id);

        model.addAttribute("profesor", currentProfesor);
        model.addAttribute("difusiones", serDifusion.getDifusionesCurso(curso));

        return "profesor/comunicaciones/difusiones/table";
    }

    @GetMapping("/acudiente/comunicaciones/difusiones/{id}")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACUDIENTE')")
    public String getAnunciosCurso(@PathVariable Long id, Model model) throws ProfesorNotFound, Exception{
        
        Estudiante estudiante = serEstudiante.buscarPorId(id);

        model.addAttribute("profesor", serProfesor.getById(1L));
        model.addAttribute("difusiones", serDifusion.getDifusionesCurso(estudiante.getCursoActual()));

        return "acudiente/comunicaciones/difusiones/table";
    }

    //Crear Difusión

    @GetMapping("/profesor/comunicaciones/difusiones/new/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESOR')")
    public String getDifusion(@PathVariable Long id, Model model) throws ProfesorNotFound, Exception{

        Difusion difusion = new Difusion();

        difusion.setCurso(serCurso.buscarPorId(id));
        difusion.setProfesor(serProfesor.getByUser(serUser.getLoggedUser()));
        difusion.setDate(new Date());

        model.addAttribute("difusion", difusion);

        return "profesor/comunicaciones/difusiones/form";
    }

    @PostMapping("/profesor/comunicaciones/save/difusion")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESOR')")
    public String newDifusion(@Valid @ModelAttribute("observacion") Difusion difusion, Model model) throws ProfesorNotFound{

        difusion.setCurso(serCurso.buscarPorId(difusion.getCurso().getId()));
        difusion.setProfesor(serProfesor.getById(difusion.getProfesor().getId()));
        difusion.setDate(new Date());

        serDifusion.saveDifusion(difusion);

        return "redirect:/profesor/comunicaciones";
    }

    // Borrar difusion

    @GetMapping("/profesor/comunicaciones/difusiones/delete/{id}/{id_curso}")
    public String deleteDifusion(@PathVariable String id, @PathVariable Long id_curso, Model model) throws ProfesorNotFound, Exception{

        Difusion difusion = new Difusion();

        difusion.setCurso(serCurso.buscarPorId(id_curso));
        difusion.setId(id);

        serDifusion.deleteDifusion(difusion);

        return "redirect:/profesor/comunicaciones";
    }
    
    //Buscar acudientes estudiantes

    @GetMapping("/profesor/comunicaciones/acudientes/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESOR')")
    public String getAcudientes(@PathVariable Long id, Model model) throws ProfesorNotFound, Exception{

        model.addAttribute("acudientes", serAcudiente.getAcudientesEstudiante(serEstudiante.buscarPorId(id)));
        return "profesor/comunicaciones/chats/selectacudientes";
    }
}