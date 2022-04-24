package com.app.akdemy.repository;

import java.util.List;
import java.util.Optional;

import com.app.akdemy.entity.Estudiante;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends CrudRepository<Estudiante,Long>{
    public List<Estudiante> findByNombres(String nombres);
    public Optional<Estudiante> findByDocumento(String documento);
}
