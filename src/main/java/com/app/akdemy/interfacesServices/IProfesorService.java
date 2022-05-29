package com.app.akdemy.interfacesServices;

import java.util.List;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.dto.CalificacionDTO;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.entity.User;

public interface IProfesorService {
    public void saveProfesor(Profesor profesor);
    public List<Profesor> getAllProfesors();
    public Profesor getById(Long id) throws ProfesorNotFound;
    public void deleteProfesor(Profesor profesor) throws Exception;
    public Profesor getByUser(User user)throws ProfesorNotFound;
    public void guardarCalificaciones(CalificacionDTO calificaciones, boolean cerrar)throws ProfesorNotFound,Exception;
}
