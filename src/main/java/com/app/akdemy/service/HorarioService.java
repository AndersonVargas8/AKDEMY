package com.app.akdemy.service;

import java.util.List;

import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.HorarioCurso;
import com.app.akdemy.entity.MateriaGrado;
import com.app.akdemy.entity.Profesor;
import com.app.akdemy.interfacesServices.ICursoService;
import com.app.akdemy.interfacesServices.IHorarioService;
import com.app.akdemy.interfacesServices.IProfesorService;
import com.app.akdemy.repository.HorarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorarioService implements IHorarioService {
    @Autowired
    HorarioRepository repHorario;

    @Autowired
    ICursoService serCurso;

    @Autowired
    IProfesorService serProfesor;

    @Override
    public List<HorarioCurso> obtenerTodos() {
        return (List<HorarioCurso>) repHorario.findAll();
    }

    @Override
    public HorarioCurso obtenerPorId(long id) {
        return repHorario.findById(id).get();
    }

    @Override
    public void guardarHorario(HorarioCurso horario) {
        repHorario.save(horario);
    }

    @Override
    public void eliminarHorario(long idHorario) {
        repHorario.deleteById(idHorario);
    }

    @Override
    public List<HorarioCurso> obtenerPorCurso(long idCurso) {
        Curso curso = serCurso.buscarPorId(idCurso);
        return repHorario.findByCurso(curso);
    }

    @Override
    public List<HorarioCurso> obtenerPorProfesor(Profesor profesor) {
        return (List<HorarioCurso>) repHorario.findByProfesor(profesor);
    }

    @Override
    public Profesor getProfesorByCursoAndMateria(Curso curso, MateriaGrado materia) {
        List<Profesor> profesores = repHorario.findProfesorByCursoAndMateria(curso, materia);

        if (profesores.isEmpty())
            return null;

        return profesores.get(0);
    }

}
