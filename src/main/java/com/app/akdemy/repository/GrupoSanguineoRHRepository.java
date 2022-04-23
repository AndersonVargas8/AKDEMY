package com.app.akdemy.repository;

import com.app.akdemy.entity.GrupoSanguineoRH;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoSanguineoRHRepository extends CrudRepository<GrupoSanguineoRH, Integer>{
    
}
