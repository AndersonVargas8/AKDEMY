package com.app.akdemy.interfacesServices;

import com.app.akdemy.Exception.MateriaNotFound;
import com.app.akdemy.entity.Materia;

import java.util.List;

public interface IMateriaService {

    public void saveMateria(Materia materia);
    public List<Materia> getAllMaterias();
    public Materia buscarPorId(long id);
    public void deleteMateria(Long id) throws Exception;
    public Materia getById(Long id) throws MateriaNotFound;
    
}
