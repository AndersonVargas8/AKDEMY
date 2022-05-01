package com.app.akdemy.repository;

import com.app.akdemy.entity.Materia;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepository extends CrudRepository<Materia, Long>{
    
}
