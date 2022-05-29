package com.app.akdemy.repository;

import com.app.akdemy.entity.Periodo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodoRepository extends CrudRepository<Periodo,Long>{
    
}
