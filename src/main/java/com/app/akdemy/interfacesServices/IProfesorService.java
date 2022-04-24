package com.app.akdemy.interfacesServices;

import java.util.List;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.entity.Profesor;

public interface IProfesorService {
    public void saveProfesor(Profesor profesor);
    public List<Profesor> getAllProfesors();
    public Profesor getById(Long id) throws ProfesorNotFound;
    public void deleteProfesor(Profesor profesor) throws Exception;
}
