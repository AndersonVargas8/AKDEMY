package com.app.akdemy.service;

import java.util.List;

import com.app.akdemy.dto.CalificacionDTO;
import com.app.akdemy.dto.EstudianteCalificacionDTO;
import com.app.akdemy.entity.Calificacion;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.Periodo;
import com.app.akdemy.interfacesServices.ICalificacionesService;
import com.app.akdemy.interfacesServices.ICursoService;
import com.app.akdemy.interfacesServices.IMateriaGradoService;
import com.app.akdemy.repository.CalificacionRepository;
import com.app.akdemy.repository.PeriodoRepository;
import com.app.akdemy.repository.ProfesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalificacionesService implements ICalificacionesService {
    @Autowired
    private PeriodoRepository repPeriodo;

    @Autowired
    private CalificacionRepository repCalificacion;

    @Autowired
    private ICursoService serCurso;

    @Autowired 
    private IMateriaGradoService serMateria;

    @Autowired 
    ProfesorRepository repProfesor;

    @Override
    public List<Periodo> getAllPeriodos() {
        return (List<Periodo>) repPeriodo.findAll();
    }

    @Override
    public CalificacionDTO findEstudiantesCalificaciones(int idCurso, int idMateria, int idPeriodo, long idProfesor){
        
        List<Calificacion> calificaciones = repCalificacion.findByMateriaAndProfesorAndPeriodoAndCurso(idMateria,
                idProfesor, idPeriodo, idCurso);
        List<Estudiante> estudiantes = serCurso.buscarPorId(idCurso).getEstudiantes();

        CalificacionDTO estudiantesCal = new CalificacionDTO();

        int cont = 0;
        if (!calificaciones.isEmpty()) {

            Calificacion cal = calificaciones.get(0);
            estudiantesCal.setProfesor(cal.getProfesor());
            estudiantesCal.setCurso(cal.getEstudiante().getCursoActual());
            estudiantesCal.setMateria(cal.getMateria());
            estudiantesCal.setPeriodo(cal.getPeriodo());
            for (Calificacion calificacion : calificaciones) {
                estudiantes.remove(calificacion.getEstudiante());
                EstudianteCalificacionDTO estudiante = new EstudianteCalificacionDTO();
                estudiante.mapEstudiante(calificacion.getEstudiante());
                estudiante.setNota(calificacion.getNota());
                estudiante.setCerrada(calificacion.isCerrada());
                estudiante.setIdCalificacion(calificacion.getId());
                estudiantesCal.getEstudiantes().add(estudiante);

                if(calificacion.isCerrada())
                    cont++;
            }

        }else{
            estudiantesCal.setProfesor(repProfesor.findById(idProfesor).get());
            estudiantesCal.setCurso(serCurso.buscarPorId(idCurso));
            estudiantesCal.setMateria(serMateria.buscarPorId(idMateria));
            estudiantesCal.setPeriodo(repPeriodo.findById((long)idPeriodo).get());
        }

        if (!estudiantes.isEmpty()) {
            for (Estudiante est : estudiantes) {
                EstudianteCalificacionDTO estudiante = new EstudianteCalificacionDTO();
                estudiante.mapEstudiante(est);
                estudiantesCal.getEstudiantes().add(estudiante);
            }
        }

        if(cont == estudiantesCal.getEstudiantes().size())
            estudiantesCal.setCerrada(true);
        return estudiantesCal;
    }

    @Override
    public void saveAllCalificaciones(Iterable<Calificacion> calificaciones) {
        repCalificacion.saveAll(calificaciones);   
    }

    @Override
    public List<Calificacion> findCalficacionesByEstudiante(Estudiante estudiante) {
        List<Calificacion> calificaciones = repCalificacion.findByEstudiante(estudiante);
        
        if(!calificaciones.isEmpty())
            return calificaciones;

        return null;
    }

}
