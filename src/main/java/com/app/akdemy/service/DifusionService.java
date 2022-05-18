package com.app.akdemy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Difusion;
import com.app.akdemy.firebase.FirebaseInitialize;
import com.app.akdemy.interfacesServices.IDifusionService;
import com.app.akdemy.interfacesServices.IProfesorService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DifusionService implements IDifusionService{

    @Autowired
    private FirebaseInitialize firebase;

    @Autowired
    private IProfesorService serProfesor;

    private CollectionReference getCollection(Long id){
        return firebase.getFirestore().collection("Broadcast")
        .document(Long.toString(id)).collection("messages");
    }

    @Override
    public void saveDifusion(Difusion difusion) {

        // Create Document to save in Database
        Map<String, Object> docData = new HashMap<String, Object>();
        docData.put("subject", difusion.getSubject());
        docData.put("message", difusion.getMessage());
        docData.put("date", difusion.getDate().getTime());
        docData.put("profesor", difusion.getProfesor().getId());
        docData.put("curso", difusion.getCurso().getId());

        // Set reference to save document
        CollectionReference Broadcast = getCollection(difusion.getCurso().getId());

        // Save document
        Broadcast.document().create(docData);



    }

    @Override
    public void deleteDifusion(Difusion difusion) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterable<Difusion> getDifusionesCurso(Curso curso) {
        
        // Return Iterable
        List<Difusion> difusiones = new ArrayList<Difusion>();

        // Query Collection
        ApiFuture<QuerySnapshot> querySnapshot = getCollection(curso.getId()).get();

        try {
            // Iterate results and crete objects list
            for(DocumentSnapshot docSnapshot : querySnapshot.get().getDocuments()){
                Map<String, Object> data = docSnapshot.getData();
                difusiones.add(new Difusion(
                    docSnapshot.getId(), 
                    curso, 
                    serProfesor.getById((Long) data.get("profesor")), 
                    ((Long) data.get("date")), 
                    ((String) data.get("subject")), 
                    ((String) data.get("message"))
                    ));
            }

            // Cast and return collection
            return (Iterable<Difusion>) difusiones;
            
        } catch (Exception e) {

            return null;
        }


    }

}
