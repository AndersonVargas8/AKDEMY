package com.app.akdemy.service;

import java.util.List;
import java.util.Optional;

import com.app.akdemy.Exception.AcudienteNotFound;
import com.app.akdemy.Exception.CustomeFieldValidationException;
import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.User;
import com.app.akdemy.interfacesServices.IAcudienteService;
import com.app.akdemy.repository.AcudienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcudienteService implements IAcudienteService {
    @Autowired
    AcudienteRepository repAcudiente;

    @Override
    public List<Acudiente> getAllAcudientes() {
        return (List<Acudiente>) repAcudiente.findAll();
    }

    @Override
    public void saveAcudiente(Acudiente acudiente) {
        repAcudiente.save(acudiente);
    }

    @Override
    public Acudiente getById(long id) throws AcudienteNotFound {
        Acudiente acudiente = repAcudiente.findById(id)
                .orElseThrow(() -> new AcudienteNotFound("El acudiente no ha sido encontrado"));
        return acudiente;
    }


    @Override
    public void deleteAcudiente(Acudiente acudiente) {
        repAcudiente.delete(acudiente);

    }

    @Override
    public Acudiente getByUser(User user) throws AcudienteNotFound {
        Acudiente acudiente = repAcudiente.findByUsuario(user)
                .orElseThrow(() -> new AcudienteNotFound("El acudiente no ha sido encontrado"));
        return acudiente;
    }

    private boolean checkAcudienteExiste(Acudiente acudiente) throws Exception {
        Optional<Acudiente> acudienteEncontrado = repAcudiente.findByDocumento(acudiente.getDocumento());
        if (acudienteEncontrado.isPresent()) {
            throw new CustomeFieldValidationException("Ya existe un acudiente con este documento", "documento");
        }
        return false;
    }

    @Override
    public boolean validarAcudiente(Acudiente acudiente) throws Exception {
        checkAcudienteExiste(acudiente);
        return true;
    }

    public Acudiente getbyUser(User usuario) {
        return repAcudiente.findByUsuario(usuario).get();
    }

    @Override
    public Iterable<Acudiente> getAcudientesEstudiante(Estudiante estudiante) {
        return repAcudiente.getAcudientesEstudiante(estudiante.getId());
    }

    
    
}
