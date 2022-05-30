package com.app.akdemy.repository;

import com.app.akdemy.entity.Acudiente;

import org.springframework.data.jpa.repository.Query;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AcudienteRepository extends CrudRepository<Acudiente, Long> {
    public Optional<Acudiente> findByUsuario(User user);

    public Optional<Acudiente> findByDocumento(String documento);
}
