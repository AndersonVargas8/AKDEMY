package com.app.akdemy.repository;

import java.util.Optional;

import com.app.akdemy.entity.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
    Optional<User> findByUsername(String username);

    @Query(value="select u.id, password, username from user u left join profesor p on u.id = p.pro_usuario where p.pro_usuario is null", nativeQuery=true)
    Iterable<User> usersAvaliablesProfesores();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_roles values(:userID, 3)", nativeQuery = true)
    void setRoleProfesor(@Param("userID") Long userID);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user_roles WHERE user_id = :userID AND role_id = 3", nativeQuery = true)
    void removeRoleProfesor(@Param("userID") Long userID);


    @Query(value="select u.id, password, username from user u left join acudiente a on u.id = a.pro_usuario where a.pro_usuario is null", nativeQuery=true)
    Iterable<User> usersAvaliablesAcudientes();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO user_roles values(:userID, 4)", nativeQuery = true)
    void setRoleAcudiente(@Param("userID") Long userID);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user_roles WHERE user_id = :userID AND role_id = 4", nativeQuery = true)
    void removeRoleAcudiente(@Param("userID") Long userID);

}
