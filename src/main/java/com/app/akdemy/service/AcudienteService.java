package com.app.akdemy.service;

import java.util.List;

import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.interfacesServices.IAcudienteService;
import com.app.akdemy.repository.AcudienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcudienteService implements IAcudienteService{
    @Autowired
    AcudienteRepository repAcudiente;

    @Override
    public Acudiente guardarAcudiente(Acudiente acudiente) {
        return repAcudiente.save(acudiente);
    }

    @Override
    public List<Acudiente> obtenerTodo() {
        return (List<Acudiente>)repAcudiente.findAll();
    }
    
}
