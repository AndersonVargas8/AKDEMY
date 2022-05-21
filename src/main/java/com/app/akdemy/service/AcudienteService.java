package com.app.akdemy.service;

import java.util.List;

import com.app.akdemy.Exception.AcudienteNotFound;
import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.entity.User;
import com.app.akdemy.interfacesServices.IAcudienteService;
import com.app.akdemy.repository.AcudienteRepository;

import com.app.akdemy.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcudienteService implements IAcudienteService{
    @Autowired
    AcudienteRepository repAcudiente;

    @Override
    public List<Acudiente> getAllAcudientes() {
        return (List<Acudiente>)repAcudiente.findAll();
    }

    @Override
    public void saveAcudiente(Acudiente acudiente) {
        repAcudiente.save(acudiente);
    }

    @Override
    public Acudiente getById(Long id) throws AcudienteNotFound {
        Acudiente acudiente = repAcudiente.findById(id).orElseThrow(() -> new AcudienteNotFound("El acudiente no ha sido encontrado"));
        return acudiente;
    }

    @Override
    public void deleteAcudiente(Acudiente acudiente) {
        repAcudiente.delete(acudiente);

    }

    @Override
    public Acudiente getByUser(User user) throws AcudienteNotFound {
        Acudiente acudiente = repAcudiente.findByUsuario(user).orElseThrow(() -> new AcudienteNotFound("El acudiente no ha sido encontrado"));
        return acudiente;
    }



}
