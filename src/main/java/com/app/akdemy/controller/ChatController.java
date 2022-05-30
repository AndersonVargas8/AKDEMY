package com.app.akdemy.controller;

import java.util.Date;

import javax.validation.Valid;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.dto.Message;
import com.app.akdemy.entity.Chat;
import com.app.akdemy.interfacesServices.IAcudienteService;
import com.app.akdemy.interfacesServices.IChatService;
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
public class ChatController {


    @Autowired
    IChatService serChat;

    @Autowired
    UserService serUser;

    @Autowired
    IProfesorService serProfesor;

    @Autowired
    IAcudienteService serAcudiente;

    // Recuperar Chat

    @GetMapping("/profesor/comunicaciones/chat/{id}")
    @PreAuthorize("hasAnyRole('ROLE_PROFESOR')")
    public String getChat(@PathVariable String id, Model model) throws ProfesorNotFound, Exception{

        Chat chat = serChat.getChat(id);

        //Validate user
        if(chat.getProfesor() != serProfesor.getByUser(serUser.getLoggedUser())){
            return "redirect:/profesor/comunicaciones";
        }

        model.addAttribute("chat", chat);
        model.addAttribute("messages", serChat.getMessages(chat));
        model.addAttribute("message", new Message());
        model.addAttribute("id", id);

        return "profesor/comunicaciones/chats/index";
    }

    @GetMapping("/acudiente/comunicaciones/chat/{id}")
    //@PreAuthorize("hasAnyRole('ROLE_ACUDIENTE')")
    public String getChatAcudiente(@PathVariable String id, Model model) throws Exception{

        Chat chat = serChat.getChat(id);

        //Validate user
        if(chat.getAcudiente() != serAcudiente.getByUser(serUser.getLoggedUser()) ){
            return "redirect:/acudiente/comunicaciones";
        }

        model.addAttribute("chat", chat);
        model.addAttribute("messages", serChat.getMessages(chat));
        model.addAttribute("message", new Message());
        model.addAttribute("id", id);

        return "acudiente/comunicaciones/chats/index";
    }


    // Enviar mensaje

    @PostMapping("/profesor/comunicaciones/chat/{id}")
    @PreAuthorize("hasAnyRole('ROLE_PROFESOR')")
    public String sendMessage(@PathVariable String id, @ModelAttribute("message") Message message, Model model) { 

        message.setDate(new Date());
        message.setProfesor(true);
        Chat chat = serChat.getChat(id);
        serChat.saveMessage(message, chat);

        return String.format("redirect:/profesor/comunicaciones/chat/%s", id);
    }

    @PostMapping("/acudiente/comunicaciones/chat/{id}")
    //@PreAuthorize("hasAnyRole('ROLE_ACUDIENTE')")
    public String sendMessageAcudiente(@PathVariable String id, @ModelAttribute("message") Message message, Model model) { 

        message.setDate(new Date());
        message.setProfesor(false);
        Chat chat = serChat.getChat(id);
        serChat.saveMessage(message, chat);

        return String.format("redirect:/acudiente/comunicaciones/chat/%s", id);
    }



    // Crear Chat
    
    @PostMapping("/profesor/comunicaciones/save/chat")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESOR')")
    public String newChat(@Valid @ModelAttribute("chat") Chat chat, Model model){

        String id = "chat-" + chat.getEstudiante().getId() + "-" + chat.getAcudiente().getId() + "-" + chat.getProfesor().getId();

        chat.setLastUpdate(new Date());

        serChat.saveChat(chat);

        return String.format("redirect:/profesor/comunicaciones/chat/%s", id);
    }

    @PostMapping("/acudiente/comunicaciones/save/chat")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ACUDIENTE')")
    public String newChatAcudiente(@Valid @ModelAttribute("chat") Chat chat, Model model){

        String id = "chat-" + chat.getEstudiante().getId() + "-" + chat.getAcudiente().getId() + "-" + chat.getProfesor().getId();

        chat.setLastUpdate(new Date());

        serChat.saveChat(chat);

        return String.format("redirect:/acudiente/comunicaciones/chat/%s", id);
    }

}
