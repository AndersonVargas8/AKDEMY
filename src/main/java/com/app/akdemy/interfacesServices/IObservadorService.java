package com.app.akdemy.interfacesServices;

import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.entity.Observador;

public interface IObservadorService {

    Iterable<Observador> getObservadorEstudiante(Estudiante estudiante);
    void saveObservador(Observador observador);
    Observador getObservadorByID(Long id);
    void deleteObservador(Observador observador);
    
}
