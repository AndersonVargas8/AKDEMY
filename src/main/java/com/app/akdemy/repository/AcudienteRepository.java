package com.app.akdemy.repository;

import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.User;
import com.google.common.base.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AcudienteRepository extends CrudRepository<Acudiente, Long>{
    
    @Query(value = "SELECT * FROM acudiente JOIN acudiente_estudiante ae on acudiente.id = ae.id_acudiente WHERE id_estudiante = :estudianteID", nativeQuery = true)
    Iterable<Acudiente> getAcudientesEstudiante(@Param("estudianteID") Long estudianteID);
    Optional<Acudiente> findByUsuario(User usuario);
}
