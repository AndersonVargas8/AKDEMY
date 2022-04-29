package com.app.akdemy.repository;

import com.app.akdemy.entity.MateriaGrado;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaGradoRepository extends CrudRepository<MateriaGrado, Long>{
    
}
