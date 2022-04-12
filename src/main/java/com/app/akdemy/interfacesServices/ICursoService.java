package com.app.akdemy.interfacesServices;

import java.util.List;

import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Estudiante;

public interface ICursoService {
    public List<Curso> obtenerTodos();
    public Curso buscarPorDescripcion(String descripcion);
}
