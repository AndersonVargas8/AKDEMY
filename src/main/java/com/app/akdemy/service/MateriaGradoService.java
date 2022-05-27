package com.app.akdemy.service;

import java.util.List;

import com.app.akdemy.entity.MateriaGrado;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.interfacesServices.ICursoService;
import com.app.akdemy.interfacesServices.IMateriaGradoService;
import com.app.akdemy.repository.MateriaGradoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaGradoService implements IMateriaGradoService{

    @Autowired
    MateriaGradoRepository repMateriaGrado;

    @Autowired
    ICursoService serCurso;

    @Override
    public void saveMateriaGrado(MateriaGrado materiaGrado) {
        repMateriaGrado.save(materiaGrado);
    }

    @Override
    public List<MateriaGrado> getAllMateriasGrado() {
        return (List<MateriaGrado>) repMateriaGrado.findAll();
    }

    @Override
    public MateriaGrado buscarPorId(long id) {
        return repMateriaGrado.findById(id).get();
    }

    @Override
    public void deleteMateriaGrado(Long id) throws Exception {
        MateriaGrado materiaGrado = repMateriaGrado.findById(id)
				.orElseThrow(() -> new Exception("SchoolSubjectnotFound in deleteUser -"+this.getClass().getName()));

		repMateriaGrado.delete(materiaGrado);
    }

    @Override
    public List<MateriaGrado> getByCursoAndProfesor(int idCurso, Profesor profesor) {
        List<MateriaGrado> materias = repMateriaGrado.getMateriasByCursoAndProfesor(serCurso.buscarPorId(idCurso),profesor);

        if(materias.isEmpty()){
            return null;
        }
        return materias;
    }
    
}
