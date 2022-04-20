package com.app.akdemy.service;

import java.util.List;

import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.interfacesServices.IEstudianteService;
import com.app.akdemy.repository.EstudianteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService implements IEstudianteService {
    EstudianteRepository repEstudiante;

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
        repEstudiante.save(estudiante);

    }

    @Override
    public List<Estudiante> listarEstudiantes() {
        return (List<Estudiante>) repEstudiante.findAll();
    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        repEstudiante.delete(estudiante);

    }

}
