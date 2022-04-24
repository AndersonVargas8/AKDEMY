package com.app.akdemy.repository;

import java.util.Optional;

import com.app.akdemy.entity.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
    Optional<User> findByUsername(String username);

    @Query(value="select u.id, password, username from user u left join profesor p on u.id = p.pro_usuario where p.pro_usuario is null", nativeQuery=true)
    Iterable<User> usersAvaliablesProfesores();
}
