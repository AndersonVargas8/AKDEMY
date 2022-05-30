package com.app.akdemy.repository;

import java.util.List;

import com.app.akdemy.entity.Calificacion;
import com.app.akdemy.entity.Estudiante;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepository extends CrudRepository<Calificacion, Long> {
    @Query(value = "SELECT c.* FROM calificacion c JOIN curso_estudiante ON c.cal_estudiante = curso_estudiante.id_estudiante WHERE c.cal_materia = :idMateria AND c.cal_profesor = :idProfesor AND c.cal_periodo = :idPeriodo AND curso_estudiante.id_curso = :idCurso", nativeQuery = true)
    public List<Calificacion> findByMateriaAndProfesorAndPeriodoAndCurso(@Param("idMateria") long idMateria,
            @Param("idProfesor") long idProfesor, @Param("idPeriodo") long idPeriodo, @Param("idCurso") long idCurso);

    public List<Calificacion> findByEstudiante(Estudiante estudiante);
}
