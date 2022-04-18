package com.app.akdemy.service;

import java.util.List;

import com.app.akdemy.entity.Profesor;
import com.app.akdemy.interfacesServices.IProfesorService;
import com.app.akdemy.repository.ProfesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorService implements IProfesorService{

    @Autowired
    ProfesorRepository repProfesor;

    @Override
    public List<Profesor> getAllProfesors() {
        return (List<Profesor>) repProfesor.findAll();
    }
}
