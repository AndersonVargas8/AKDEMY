package com.app.akdemy.repository;

import java.util.Optional;

import com.app.akdemy.entity.Curso;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends CrudRepository<Curso,Long>{
    public Optional<Curso> findById(Long id);

    public Optional<Curso> findByDescripcion(String descripcion);
}
