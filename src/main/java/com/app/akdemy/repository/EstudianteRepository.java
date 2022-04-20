package com.app.akdemy.repository;

import com.app.akdemy.entity.Estudiante;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends CrudRepository<Estudiante, Long> {
    public Estudiante findByName(String name);
}