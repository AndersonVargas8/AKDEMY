package com.app.akdemy.repository;

import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.Observador;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObservadorRepository extends CrudRepository<Observador, Long> {
    
    Iterable<Observador> findByEstudiante(Estudiante estudiante);
}
