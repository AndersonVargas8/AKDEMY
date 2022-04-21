package com.app.akdemy.service;

import java.util.List;

import com.app.akdemy.entity.Role;
import com.app.akdemy.interfacesServices.IRoleService;
import com.app.akdemy.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService{
    @Autowired
    RoleRepository repRole;

    @Override
    public Role buscarPorNombre(String nombre) {
        return repRole.findByName(nombre);
    }

    @Override
    public List<Role> obtenerTodos() {
        return (List<Role>)repRole.findAll();
    }
    
}
