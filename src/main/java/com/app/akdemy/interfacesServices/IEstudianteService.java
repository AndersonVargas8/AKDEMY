package com.app.akdemy.interfacesServices;

import java.util.List;

import com.app.akdemy.entity.Estudiante;

public interface IEstudianteService {
    public void guardarEstudiante(Estudiante estudiante);

    public void eliminarEstudiante(Estudiante estudiante);

    public List<Estudiante> listarEstudiantes();

    public Estudiante buscarPorId(long id);

    public boolean validarEstudiante(Estudiante estudiante)throws Exception;
    
}
