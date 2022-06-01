package com.app.akdemy.repository;

import java.util.Optional;

import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.User;

import org.springframework.data.jpa.repository.Query;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface AcudienteRepository extends CrudRepository<Acudiente, Long> {
    public Optional<Acudiente> findByUsuario(User user);

    public Optional<Acudiente> findByDocumento(String documento);
    
    @Query(value = "SELECT * FROM acudiente JOIN acudiente_estudiante ae on acudiente.id = ae.id_acudiente WHERE id_estudiante = :estudianteID", nativeQuery = true)
    Iterable<Acudiente> getAcudientesEstudiante(@Param("estudianteID") Long estudianteID);
}
