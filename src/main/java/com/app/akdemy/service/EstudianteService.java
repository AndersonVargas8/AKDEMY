package com.app.akdemy.service;

import java.util.List;
import java.util.Optional;

import com.app.akdemy.Exception.CustomeFieldValidationException;
import com.app.akdemy.dto.CalificacionesEstDTO;
import com.app.akdemy.entity.Calificacion;
import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.MateriaGrado;
import com.app.akdemy.entity.Periodo;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.entity.User;
import com.app.akdemy.interfacesServices.ICalificacionesService;
import com.app.akdemy.interfacesServices.IEstudianteService;
import com.app.akdemy.interfacesServices.IHorarioService;
import com.app.akdemy.interfacesServices.IMateriaGradoService;
import com.app.akdemy.repository.EstudianteRepository;
import com.app.akdemy.repository.HorarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService implements IEstudianteService {
    @Autowired
    EstudianteRepository repEstudiante;

    @Autowired
    IMateriaGradoService serMateriaGrado;

    @Autowired
    ICalificacionesService serCalificaciones;

    @Autowired
    HorarioRepository repHorario;

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
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
            throw new CustomeFieldValidationException("Ya existe un estudiante con este documento", "documento");
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
        if (estudiante.isPresent())
            return estudiante.get();
        return null;
    }

    @Override
    public Iterable<Estudiante> getEstudiantesCursoID(Long id) {
        return repEstudiante.getEstudiantesbyCurso(id);
    }


//    @Override
//    public Iterable<Estudiante> getEstudiantesAcudienteID(Long id) {
//        return repEstudiante.getEstudiantesByAcudiente(id);
//    }

    @Override
    public CalificacionesEstDTO getCalificaciones(Estudiante estudiante) {
        List<Periodo> periodos = serCalificaciones.getAllPeriodos();

        CalificacionesEstDTO calificacionesEstudiante = new CalificacionesEstDTO(periodos);
        calificacionesEstudiante.setNombreEstudiante(estudiante.getNombres().concat(" " + estudiante.getApellidos()));

        if (estudiante.getCursoActual() == null) {
            return calificacionesEstudiante;
        }
        calificacionesEstudiante.setCurso(estudiante.getCursoActual().getNombre_Curso());

        List<MateriaGrado> materias = serMateriaGrado.getByCurso(estudiante.getCursoActual());

        if (materias == null)
            return calificacionesEstudiante;

        List<Calificacion> calificaciones = serCalificaciones.findCalficacionesByEstudiante(estudiante);

        if (calificaciones != null) {
            for (Calificacion calificacion : calificaciones) {
                materias.remove(calificacion.getMateria());
                calificacionesEstudiante.agregarCalificacion(calificacion);
            }
        }

        if (!materias.isEmpty()) {
            for (MateriaGrado materia : materias) {
                List<Profesor> profesores = repHorario.findProfesorByCursoAndMateria(estudiante.getCursoActual(),
                        materia);
                Profesor profesor;
                if (!profesores.isEmpty()) {
                    profesor = profesores.get(0);
                } else {
                    profesor = new Profesor();
                    profesor.setNombres("Sin");
                    profesor.setApellidos("Profesor");
                }
                calificacionesEstudiante.agregarMateria(materia, profesor);
            }
        }

        return calificacionesEstudiante;
    }

    public Iterable<Estudiante> getEstudiantesAcudiente(Acudiente acudiente) {
        return repEstudiante.getEstudiantesByAcudiente(acudiente.getId());
    }

}
