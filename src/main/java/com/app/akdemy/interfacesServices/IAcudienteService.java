package com.app.akdemy.interfacesServices;

import java.util.List;

import com.app.akdemy.Exception.AcudienteNotFound;
import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.User;

public interface IAcudienteService {
    public void saveAcudiente(Acudiente acudiente);

    public List<Acudiente> getAllAcudientes();

    public Acudiente getById(Long id) throws AcudienteNotFound;

    public void deleteAcudiente(Acudiente acudiente) throws Exception;

    public Acudiente getByUser(User user) throws AcudienteNotFound;

    public boolean validarAcudiente(Acudiente acudiente) throws Exception;
    
    public Iterable<Acudiente> getAcudientesEstudiante(Estudiante estudiante);
}
