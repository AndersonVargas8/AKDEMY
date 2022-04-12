package com.app.akdemy.service;

import java.util.List;

import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.interfacesServices.IEstudianteService;
import com.app.akdemy.repository.EstudianteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService implements IEstudianteService {
    @Autowired
    EstudianteRepository repEstudiante;

    @Autowired
    CursoService serCurso;

    public void guardarEstudiante(Estudiante estudiante){
        repEstudiante.save(estudiante);
    }
    @Override
    public List<Estudiante> buscarPorCurso(int numCurso) {
        Curso curso = serCurso.buscarPorDescripcion(Integer.toString(numCurso));
        List<Estudiante> estudiantes =  repEstudiante.findByCurso(curso);
        return estudiantes;
    }
}
