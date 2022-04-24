package com.app.akdemy.interfacesServices;

import java.util.List;

import com.app.akdemy.entity.Estudiante;

public interface IEstudianteService {
    public void guardarEstudiante(Estudiante estudiante) throws Exception;

    public void eliminarEstudiante(Estudiante estudiante);

    public List<Estudiante> listarEstudiantes();

    public Estudiante buscarPorId(long id);
    
}
