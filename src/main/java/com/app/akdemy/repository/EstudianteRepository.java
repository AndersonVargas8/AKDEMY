package com.app.akdemy.repository;

import java.util.List;

import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Estudiante;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends CrudRepository<Estudiante,Integer>{
    public List<Estudiante> findByNombres(String nombres);
}
