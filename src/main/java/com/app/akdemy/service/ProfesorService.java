package com.app.akdemy.service;

import java.util.ArrayList;
import java.util.List;

import com.app.akdemy.Exception.ProfesorNotFound;
import com.app.akdemy.dto.CalificacionDTO;
import com.app.akdemy.dto.EstudianteCalificacionDTO;
import com.app.akdemy.entity.Calificacion;
import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.MateriaGrado;
import com.app.akdemy.entity.Periodo;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.entity.User;
import com.app.akdemy.interfacesServices.ICalificacionesService;
import com.app.akdemy.interfacesServices.IEstudianteService;
import com.app.akdemy.interfacesServices.IProfesorService;
import com.app.akdemy.repository.ProfesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorService implements IProfesorService {

    @Autowired
    ProfesorRepository repProfesor;

    @Autowired
    IEstudianteService serEstudiante;

    @Autowired
    ICalificacionesService serCalificaciones;

    @Override
    public List<Profesor> getAllProfesors() {
        return (List<Profesor>) repProfesor.findAll();
    }

    @Override
    public void saveProfesor(Profesor profesor) {
        repProfesor.save(profesor);

    }

    @Override
    public Profesor getById(Long id) throws ProfesorNotFound {
        Profesor profesor = repProfesor.findById(id)
                .orElseThrow(() -> new ProfesorNotFound("El profesor no ha sido encontrado"));
        return profesor;
    }

    @Override
    public void deleteProfesor(Profesor profesor) {
        repProfesor.delete(profesor);

    }

    @Override
    public Profesor getByUser(User user) throws ProfesorNotFound {
        Profesor profesor = repProfesor.findByUsuario(user)
                .orElseThrow(() -> new ProfesorNotFound("El profesor no ha sido encontrado"));
        return profesor;
    }

    @Override
    public void guardarCalificaciones(CalificacionDTO calificaciones, boolean cerrar) throws ProfesorNotFound, Exception {
        MateriaGrado materia = calificaciones.getMateria();
        Periodo periodo = calificaciones.getPeriodo();
        Profesor profesor = calificaciones.getProfesor();
        List<EstudianteCalificacionDTO> estudiantesCal = calificaciones.getEstudiantes();
        List<Calificacion> calificacionesList = new ArrayList<>();
        
        for (EstudianteCalificacionDTO cal : estudiantesCal) {
            if(cal.isCerrada())
                continue;
            if (cal.getNota() != null || cerrar) {
                Estudiante estudiante = serEstudiante.buscarPorId(cal.getId());
                boolean cerrada = (cerrar) ? true : cal.getCerrada();
                Float nota = cal.getNota();
                if(cerrar){
                    nota = (nota == null) ? 0f : nota;
                }
                Calificacion calificacion = new Calificacion(cal.getIdCalificacion(), nota, periodo,
                        estudiante, profesor, materia, cerrada);
                calificacionesList.add(calificacion);
            }
        }

        if(!calificacionesList.isEmpty())
            serCalificaciones.saveAllCalificaciones(calificacionesList);
    }
}
