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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {


    @Autowired
    IChatService serChat;

    @GetMapping("/profesor/comunicaciones/chat/{id}")
    public String getChat(@PathVariable String id, Model model){

        Chat chat = serChat.getChat(id);

        model.addAttribute("chat", chat);
        model.addAttribute("messages", serChat.getMessages(chat));
        model.addAttribute("message", new Message());
        model.addAttribute("id", id);

        return "profesor/comunicaciones/chats/index";
    }

    @PostMapping("/profesor/comunicaciones/chat/{id}")
    public String sendMessage(@PathVariable String id, @ModelAttribute("message") Message message, Model model) { 

        message.setDate(new Date());
        message.setProfesor(true);
        Chat chat = serChat.getChat(id);
        serChat.saveMessage(message, chat);

        return String.format("redirect:/profesor/comunicaciones/chat/%s", id);
    }

}
