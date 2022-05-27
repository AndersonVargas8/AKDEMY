package com.app.akdemy.repository;

import java.util.List;

import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.MateriaGrado;
import com.app.akdemy.entity.Profesor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaGradoRepository extends CrudRepository<MateriaGrado, Long>{

    @Query(value = "SELECT DISTINCT(h.materia) FROM HorarioCurso h WHERE h.curso = ?1 AND h.profesor = ?2")
    public List<MateriaGrado> getMateriasByCursoAndProfesor(Curso curso, Profesor profesor);
}
