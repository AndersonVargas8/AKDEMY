package com.app.akdemy.repository;

import java.util.List;

import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.HorarioCurso;
import com.app.akdemy.entity.MateriaGrado;
import com.app.akdemy.entity.Profesor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorarioRepository extends CrudRepository<HorarioCurso, Long> {
    public List<HorarioCurso> findByCurso(Curso curso);

    public List<HorarioCurso> findByProfesor(Profesor profesor);
    
    @Query(value = "SELECT DISTINCT(h.curso) FROM HorarioCurso h WHERE h.profesor = ?1")
    public List<Curso> findCursoByProfesor(Profesor profesor);

    @Query(value = "SELECT h.profesor FROM HorarioCurso h WHERE h.curso = ?1 AND h.materia = ?2")
    public List<Profesor> findProfesorByCursoAndMateria(Curso curso, MateriaGrado materia);
}
