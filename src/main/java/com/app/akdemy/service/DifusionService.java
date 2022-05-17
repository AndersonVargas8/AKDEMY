package com.app.akdemy.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Difusion;
import com.app.akdemy.firebase.FirebaseInitialize;
import com.app.akdemy.interfacesServices.IDifusionService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DifusionService implements IDifusionService{

    @Autowired
    private FirebaseInitialize firebase;

    @Override
    public void saveDifusion(Difusion difusion) {
        Map<String, Object> docData = new HashMap<String, Object>();
        docData.put("subject", difusion.getSubject());
        docData.put("message", difusion.getMessage());
        docData.put("date", difusion.getDate().getTime());
        docData.put("profesor", difusion.getProfesor().getId());
        docData.put("curso", difusion.getCurso().getId());

        CollectionReference Broadcast = firebase.getFirestore().collection("Broadcast")
        .document(Long.toString(difusion.getCurso().getId())).collection("messages");

        Broadcast.document().create(docData);



    }

    @Override
    public void deleteDifusion(Difusion difusion) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Iterable<Difusion> getDifusionesCurso(Curso curso) {
        // TODO Auto-generated method stub
        return null;
    }

}
