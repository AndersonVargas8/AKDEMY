package com.app.akdemy.service;

import java.util.List;
import java.util.Optional;

import com.app.akdemy.Exception.CustomeFieldValidationException;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.User;
import com.app.akdemy.interfacesServices.IEstudianteService;
import com.app.akdemy.repository.EstudianteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService implements IEstudianteService {
    @Autowired
    EstudianteRepository repEstudiante;

    @Override
    public void guardarEstudiante(Estudiante estudiante){
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

    @Override
    public Estudiante buscarPorId(long id) {
        return repEstudiante.findById(id).get();
    }

    private boolean checkEstudianteExiste(Estudiante estudiante) throws Exception {
        Optional<Estudiante> estudianteEncontrado = repEstudiante.findByDocumento(estudiante.getDocumento());
        if (estudianteEncontrado.isPresent()) {
            throw new CustomeFieldValidationException("Ya existe un estudiante con este documento","documento");
        }
        return false;
    }

    @Override
    public boolean validarEstudiante(Estudiante estudiante) throws Exception {
        checkEstudianteExiste(estudiante);
        return true;
    }

    @Override
    public Estudiante getByUser(User user) {
        Optional<Estudiante> estudiante = repEstudiante.findByUsuario(user);
        if(estudiante.isPresent())
            return estudiante.get();
        return null;
    }

}
