package com.app.akdemy.interfacesServices;

import java.util.List;

import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.User;

public interface IAcudienteService {
    public Acudiente guardarAcudiente(Acudiente acudiente);
    public List<Acudiente> obtenerTodo();
    public Acudiente getById(Long id);
    public Iterable<Acudiente> getAcudientesEstudiante(Estudiante estudiante);
    public Acudiente getbyUser(User usuario);
}
