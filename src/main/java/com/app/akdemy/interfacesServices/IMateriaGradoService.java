package com.app.akdemy.interfacesServices;

import java.util.List;

import com.app.akdemy.entity.MateriaGrado;

public interface IMateriaGradoService {

    public void saveMateriaGrado(MateriaGrado materiaGrado);
    public List<MateriaGrado> getAllMateriasGrado();
    public MateriaGrado buscarPorId(long id);
    public void deleteMateriaGrado(Long id) throws Exception;
    
}
