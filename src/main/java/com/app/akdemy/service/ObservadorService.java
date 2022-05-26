package com.app.akdemy.service;

import java.util.Optional;

import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.Observador;
import com.app.akdemy.interfacesServices.IObservadorService;
import com.app.akdemy.repository.ObservadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObservadorService implements IObservadorService {

    @Autowired
    private ObservadorRepository repObservador;

    @Override
    public Iterable<Observador> getObservadorEstudiante(Estudiante estudiante) {
        return repObservador.findByEstudiante(estudiante);
    }

    @Override
    public void saveObservador(Observador observador) {
        repObservador.save(observador);
    }

    @Override
    public Observador getObservadorByID(Long id) {
        return repObservador.findById(id).get();
    }

    @Override
    public void deleteObservador(Observador observador) {
        repObservador.delete(observador);
    }

    /*
     * @Override
     * public Iterable<Observador> getObservadorEstudianteByAcudiente(Acudiente
     * acudiente) {
     * return repObservador.getObservadorEstudianteAcudiente(acudiente.getId());
     * }
     */
}
