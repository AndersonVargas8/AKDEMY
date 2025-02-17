package com.app.akdemy.repository;

import com.app.akdemy.entity.Role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Long>{
    Role findByName(String name);
}
