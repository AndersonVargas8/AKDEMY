package com.app.akdemy.controller;

import java.util.Date;

import javax.management.AttributeValueExp;

import com.app.akdemy.dto.Message;
import com.app.akdemy.entity.Chat;
import com.app.akdemy.interfacesServices.IChatService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChatController {


    @Autowired
    IChatService serChat;

    @GetMapping("/chat")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ACUDIENTE')")
    public String inicioAcudiente(Model model) {
        return "profesor/comunicaciones/chats/index";
    }

    @GetMapping("/profesor/comunicaciones/chat/{id}")
    public String getChat(@PathVariable String id, Model model){

        Chat chat = serChat.getChat(id);

        Message message = new Message();

        message.setContent("holaaaaaaa");
        message.setDate(new Date());
        message.setProfesor(true);

        serChat.saveMessage(message, chat);

        model.addAttribute("chat", chat);
        model.addAttribute("messages", serChat.getMessages(chat));

        return "profesor/comunicaciones/chats/index";
    }

}
