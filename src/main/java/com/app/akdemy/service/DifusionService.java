package com.app.akdemy.service;

import java.util.Collection;

import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Difusion;
import com.app.akdemy.interfacesServices.IDifusionService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.springframework.stereotype.Service;

@Service
public class DifusionService implements IDifusionService{

    private static final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public static final String COLLECTION = "Broadcast";


    @Override
    public void saveDifusion(Difusion difusion) {

    try {
        mDatabase.child(COLLECTION).child(difusion.getCurso().getNombre_Curso() + "-" + difusion.getCurso().getAnio_Curso())
        .push().setValueAsync(difusion);
        
    } catch (Exception e) {
        e.printStackTrace();
    }
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
