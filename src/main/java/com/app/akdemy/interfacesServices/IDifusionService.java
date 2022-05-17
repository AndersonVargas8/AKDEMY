package com.app.akdemy.interfacesServices;

import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Difusion;

public interface IDifusionService {

    public void saveDifusion(Difusion difusion);
    public void deleteDifusion(Difusion difusion);
    public Iterable<Difusion> getDifusionesCurso(Curso curso);
    
}
