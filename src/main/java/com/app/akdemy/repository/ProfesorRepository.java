package com.app.akdemy.repository;

import java.util.Optional;

import com.app.akdemy.entity.Profesor;
import com.app.akdemy.entity.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends CrudRepository<Profesor,Long>{
    public Optional<Profesor> findByUsuario(User user);

   
}
