package com.app.akdemy.repository;

import com.app.akdemy.entity.TipoDocumento;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocumentoRepository extends CrudRepository<TipoDocumento,Integer>{
    
}
