package com.app.akdemy.interfacesServices;

import java.util.List;

import com.app.akdemy.entity.Acudiente;

public interface IAcudienteService {
    public Acudiente guardarAcudiente(Acudiente acudiente);
    public List<Acudiente> obtenerTodo();
}
