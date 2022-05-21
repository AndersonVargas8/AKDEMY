package com.app.akdemy.controller;

import java.util.Date;

import javax.validation.Valid;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.entity.Chat;
import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Difusion;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.interfacesServices.IChatService;
import com.app.akdemy.interfacesServices.ICursoService;
import com.app.akdemy.interfacesServices.IDifusionService;
import com.app.akdemy.interfacesServices.IProfesorService;
import com.app.akdemy.service.ChatService;
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

    @GetMapping("/acudiente/comunicaciones")
    public String index(Model model) throws ProfesorNotFound {
        Difusion Aviso = new Difusion();
        Aviso.setMessage("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        Aviso.setSubject("Interface");
        Aviso.setCurso(serCurso.buscarPorId(1L));
        Aviso.setDate(new Date());
        Aviso.setProfesor(serProfesor.getById(1L));
        serDifusion.saveDifusion(Aviso);
        model.addAttribute("difusiones", serDifusion.getDifusionesCurso(serCurso.buscarPorId(1L)));

        return "acudiente/comunicaciones/index";
    }

    @GetMapping("/profesor/comunicaciones")
    public String comunicacionesProfesor(Model model) throws ProfesorNotFound, Exception{
        Profesor currentProfesor = serProfesor.getByUser(serUser.getLoggedUser());

        Iterable<Chat> chats =  serChat.getChats(currentProfesor);

        model.addAttribute("courses", serCurso.getCoursesObservadorbyProfesor(currentProfesor));
        model.addAttribute("chats", chats);

        return "profesor/comunicaciones/index";
    }

    @GetMapping("/profesor/comunicaciones/difusiones/{id}")
    public String getObservacionesCurso(@PathVariable Long id, Model model) throws ProfesorNotFound, Exception{
        
        Profesor currentProfesor = serProfesor.getByUser(serUser.getLoggedUser());
        Curso curso = serCurso.buscarPorId(id);

        model.addAttribute("profesor", currentProfesor);
        model.addAttribute("difusiones", serDifusion.getDifusionesCurso(curso));

        return "profesor/comunicaciones/difusiones/table";
    }

    @GetMapping("/profesor/comunicaciones/difusiones/new/{id}")
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

    @GetMapping("/profesor/comunicaciones/difusiones/delete/{id}/{id_curso}")
    public String deleteDifusion(@PathVariable String id, @PathVariable Long id_curso, Model model) throws ProfesorNotFound, Exception{

        Difusion difusion = new Difusion();

        difusion.setCurso(serCurso.buscarPorId(id_curso));
        difusion.setId(id);

        serDifusion.deleteDifusion(difusion);

        return "redirect:/profesor/comunicaciones";
    }
    
}