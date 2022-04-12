package com.app.akdemy.repository;

import java.util.Optional;

import com.app.akdemy.entity.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{

    public Optional<User> findByUsername(String username);
    
}
