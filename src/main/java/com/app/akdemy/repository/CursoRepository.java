package com.app.akdemy.repository;

import java.util.List;
import java.util.Optional;

import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Profesor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long> {
    public Iterable<Curso> findByProfesor(Profesor profesor);

    @Query(value = "(SELECT  curso.id, cur_anio, cur_nombre, cur_director FROM curso JOIN horario_curso ON curso.id = hor_cur_curso WHERE cur_anio = 2022 AND hor_cur_profesor = :profesorID) UNION (SELECT * FROM curso WHERE cur_anio = 2022 AND cur_director = :profesorID)", nativeQuery = true)
    Iterable<Curso> getObservadorCursosProfesor(@Param("profesorID") Long profesorID);

    @Query(value = "(SELECT curso.id, cur_anio, cur_nombre, cur_director FROM curso JOIN profesor ON cur_director = :profesorID WHERE cur_anio = 2022 AND cur_director = :profesorID)", nativeQuery = true)
    // @Query(value = "(SELECT * FROM curso WHERE cur_anio = year(current_date) AND
    // cur_director = :profesorID)", nativeQuery= true)
    Iterable<Curso> getCursosProfesor(@Param("profesorID") Long profesorID);

    Optional<Curso> findById (Long id);
}
