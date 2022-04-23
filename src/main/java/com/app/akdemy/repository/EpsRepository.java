package com.app.akdemy.repository;

import com.app.akdemy.entity.Eps;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpsRepository extends CrudRepository<Eps, Integer>{
    
}
