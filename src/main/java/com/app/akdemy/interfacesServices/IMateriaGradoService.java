package com.app.akdemy.interfacesServices;

import java.util.List;

import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.MateriaGrado;
import com.app.akdemy.entity.Profesor;

public interface IMateriaGradoService {

    public void saveMateriaGrado(MateriaGrado materiaGrado);
    public List<MateriaGrado> getAllMateriasGrado();
    public MateriaGrado buscarPorId(long id);
    public void deleteMateriaGrado(Long id) throws Exception;
    public List<MateriaGrado> getByCursoAndProfesor(int idCurso, Profesor profesor);
    public List<MateriaGrado> getByCurso(Curso curso);
    
}
