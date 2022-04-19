package com.app.akdemy.repository;

import java.util.Optional;
import java.util.List;

import com.app.akdemy.entity.Profesor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor,Long>{
}
