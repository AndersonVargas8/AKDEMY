package com.app.akdemy.service;

import com.app.akdemy.entity.Materia;
import com.app.akdemy.interfacesServices.IMateriaService;
import com.app.akdemy.repository.MateriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService implements IMateriaService{

    @Autowired
    MateriaRepository repMateria;


    @Override
    public void saveMateria(Materia materia) {
        repMateria.save(materia);
    }

    @Override
    public List<Materia> getAllMaterias() {
        return (List<Materia>) repMateria.findAll();
    }

    @Override
    public Materia buscarPorId(long id) {
        return repMateria.findById(id).get();
    }

    @Override
    public void deleteMateria(Long id) throws Exception {
        Materia materia = repMateria.findById(id)
				.orElseThrow(() -> new Exception("SchoolSubjectnotFound in deleteSubject -"+this.getClass().getName()));

		repMateria.delete(materia);
    }


    
}
