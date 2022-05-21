package com.app.akdemy.service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import com.app.akdemy.dto.Message;
import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Chat;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.firebase.FirebaseInitialize;
import com.app.akdemy.interfacesServices.IAcudienteService;
import com.app.akdemy.interfacesServices.IChatService;
import com.app.akdemy.interfacesServices.IEstudianteService;
import com.app.akdemy.interfacesServices.IProfesorService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QuerySnapshot;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService implements IChatService{

    @Autowired
    private FirebaseInitialize firebase;

    @Autowired
    private IProfesorService serProfesor;

    @Autowired
    private IAcudienteService serAcudiente;

    @Autowired
    private IEstudianteService serEstudiante;

    private CollectionReference getCollection(){
        return firebase.getFirestore().collection("PrivateMessages");
    }

    private CollectionReference getCollection(Chat chat){
        return firebase.getFirestore().collection("PrivateMessages")
        .document(chat.getId()).collection("messages");
    }

    @Override
    public void saveChat(Chat chat) {

        // Create Document to save in Database
        Map<String, Object> docData = new HashMap<String, Object>();

        docData.put("profesor", chat.getProfesor().getId());
        docData.put("acudiente", chat.getAcudiente().getId());
        docData.put("estudiante", chat.getEstudiante().getId());
        docData.put("lastUpdate", chat.getLastUpdate().getTime());

        // Set reference to save document
        CollectionReference PrivateMessages = getCollection();

        // Save document
        PrivateMessages.document().create(docData);
        
    }

    @Override
    public void saveMessage(Message message, Chat chat) {

        // Create Document to save in Database
        Map<String, Object> docData = new HashMap<String, Object>();

        docData.put("date", message.getDate().getTime());
        docData.put("content", message.getContent());


        // Set reference to save document
        CollectionReference ChatMessages = getCollection(chat);

        // Save document
        ChatMessages.document().create(docData);

    }

    @Override
    public Chat getChat(String id) {
        ApiFuture<DocumentSnapshot> chat = getCollection().document(id).get();
        try {
            DocumentSnapshot document = chat.get();
            Map<String, Object> data = document.getData();
            return new Chat(
                id,
                serAcudiente.getById((Long) data.get("acudiente")),
                serProfesor.getById((Long) data.get("profesor")),
                serEstudiante.buscarPorId((Long) data.get("estudiante")),
                ((Long) data.get("lastUpdate"))
            );
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Iterable<Chat> getChats(Profesor profesor) {

         // Return Iterable
         List<Chat> chats = new ArrayList<Chat>();

         // Create a query against the collection.
         Query query = getCollection().whereEqualTo("profesor", profesor.getId());

         // retrieve  query results asynchronously using query.get()
         ApiFuture<QuerySnapshot> querySnapshot = query.get();
 
         try {
             // Iterate results and crete objects list
             for(DocumentSnapshot docSnapshot : querySnapshot.get().getDocuments()){
                 Map<String, Object> data = docSnapshot.getData();
                 chats.add(new Chat(
                    docSnapshot.getId(),
                    serAcudiente.getById((Long) data.get("acudiente")),
                    serProfesor.getById((Long) data.get("profesor")),
                    serEstudiante.buscarPorId((Long) data.get("estudiante")),
                    ((Long) data.get("lastUpdate"))
                 ));
             }
 
             // Cast and return collection
             return (Iterable<Chat>) chats;
             
         } catch (Exception e) {
 
             return null;
         }
  
    }

    @Override
    public Iterable<Chat> getChats(Acudiente acudiente, Estudiante estudiante) {
         // Return Iterable
         List<Chat> chats = new ArrayList<Chat>();

         // Create a query against the collection.
         Query query = getCollection().whereEqualTo("acudiente", acudiente.getId()).whereEqualTo("estudiante", estudiante.getId());

         // retrieve  query results asynchronously using query.get()
         ApiFuture<QuerySnapshot> querySnapshot = query.get();
 
         try {
             // Iterate results and crete objects list
             for(DocumentSnapshot docSnapshot : querySnapshot.get().getDocuments()){
                 Map<String, Object> data = docSnapshot.getData();
                 chats.add(new Chat(
                    docSnapshot.getId(),
                    serAcudiente.getById((Long) data.get("acudiente")),
                    serProfesor.getById((Long) data.get("profesor")),
                    serEstudiante.buscarPorId((Long) data.get("estudiante")),
                    ((Long) data.get("lastUpdate"))
                 ));
             }
 
             // Cast and return collection
             return (Iterable<Chat>) chats;
             
         } catch (Exception e) {
 
             return null;
         }
    }

}
