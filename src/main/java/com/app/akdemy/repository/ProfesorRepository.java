package com.app.akdemy.repository;

import com.app.akdemy.entity.Profesor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor,Long>{
}
