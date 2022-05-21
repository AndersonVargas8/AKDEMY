package com.app.akdemy.interfacesServices;

import com.app.akdemy.dto.Message;
import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Chat;
import com.app.akdemy.entity.Profesor;

public interface IChatService {

    public void saveChat(Chat chat);
    public Chat getChat(String id);
    public Iterable<Chat> getChats(Profesor profesor);
    public Iterable<Chat> getChats(Acudiente acudiente);
    public void saveMessage(Message message, Chat chat);
    
}
