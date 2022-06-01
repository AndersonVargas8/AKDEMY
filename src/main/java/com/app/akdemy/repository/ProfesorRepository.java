package com.app.akdemy.repository;

import java.util.List;
import java.util.Optional;

import com.app.akdemy.entity.Profesor;
import com.app.akdemy.entity.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor,Long>{
    public Optional<Profesor> findByUsuario(User user);

   
    
    @Query(value = "SELECT * FROM profesor JOIN horario_curso hc on profesor.id = hc.hor_cur_profesor WHERE hor_cur_curso = :cursoID", nativeQuery = true)
    public List<Profesor> getProfesorCurso(@Param("cursoID") Long cursoID);
}
