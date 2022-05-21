package com.app.akdemy.repository;

import com.app.akdemy.entity.Acudiente;

import com.app.akdemy.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AcudienteRepository extends CrudRepository<Acudiente, Long>{
    public Optional<Acudiente> findByUsuario(User user);
}
