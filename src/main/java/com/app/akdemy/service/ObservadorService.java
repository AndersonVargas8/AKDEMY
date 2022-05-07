package com.app.akdemy.service;

import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.Observador;
import com.app.akdemy.interfacesServices.IObservadorService;
import com.app.akdemy.repository.ObservadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObservadorService implements IObservadorService{

    @Autowired
    private ObservadorRepository repObservador;

    @Override
    public Iterable<Observador> getObservadorEstudiante(Estudiante estudiante) {
        return repObservador.findByEstudiante(estudiante);
    }
    
}
