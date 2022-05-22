package com.app.akdemy.service;

import java.util.List;

import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.interfacesServices.ICursoService;
import com.app.akdemy.repository.CursoRepository;
import com.app.akdemy.repository.HorarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService implements ICursoService {

    @Autowired
    CursoRepository repCurso;

    @Autowired
    HorarioRepository repHorario;

    @Override
    public List<Curso> getAllCourses() {
        return (List<Curso>) repCurso.findAll();
    }

    @Override
    public void saveCurso(Curso curso) {
        repCurso.save(curso);
    }

    @Override
    public Curso buscarPorId(long id) {
        return repCurso.findById(id).get();
    }

    @Override
    public void deleteCurso(Long id) throws Exception {

        Curso curso = repCurso.findById(id)
                .orElseThrow(() -> new Exception("UsernotFound in deleteUser -" + this.getClass().getName()));

        repCurso.delete(curso);
    }

    @Override
    public Iterable<Curso> getCoursesObservadorbyProfesor(Profesor profesor) {
        return repCurso.getObservadorCursosProfesor(profesor.getId());
    }

    @Override
    public List<Curso> getCursosProfesor(Profesor profesor) {
        return (List<Curso>) repHorario.findCursoByProfesor(profesor);
    }

}
