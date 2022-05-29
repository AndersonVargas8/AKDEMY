package com.app.akdemy.interfacesServices;

import java.util.List;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.dto.CalificacionDTO;
import com.app.akdemy.entity.Calificacion;
import com.app.akdemy.entity.Periodo;

public interface ICalificacionesService {
    public List<Periodo> getAllPeriodos();
    public CalificacionDTO findEstudiantesCalificaciones(int idCurso, int idMateria, int idPeriodo, long idProfesor);
    public void saveAllCalificaciones(Iterable<Calificacion> calificaciones);
}
