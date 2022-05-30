package com.app.akdemy.interfacesServices;

import java.util.List;

import com.app.akdemy.dto.CalificacionesEstDTO;
import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.MateriaGrado;
import com.app.akdemy.entity.User;

public interface IEstudianteService {
    public void guardarEstudiante(Estudiante estudiante);

    public void eliminarEstudiante(Estudiante estudiante);

    public List<Estudiante> listarEstudiantes();

    public Estudiante buscarPorId(long id);

    public boolean validarEstudiante(Estudiante estudiante) throws Exception;

    public Estudiante getByUser(User user);

    public Iterable<Estudiante> getEstudiantesCursoID(Long id);

    public CalificacionesEstDTO getCalificaciones(Estudiante estudiante);

    public Iterable<Estudiante> getEstudiantesAcudiente(Acudiente acudiente);

}
