package com.app.akdemy.interfacesServices;

import java.util.List;

import com.app.akdemy.entity.Role;

public interface IRoleService {
    public Role buscarPorNombre(String nombre);
    public List<Role> obtenerTodos();
}
