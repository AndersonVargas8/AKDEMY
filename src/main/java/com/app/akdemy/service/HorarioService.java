package com.app.akdemy.service;

import java.util.List;

import com.app.akdemy.entity.HorarioCurso;
import com.app.akdemy.interfacesServices.IHorarioService;
import com.app.akdemy.repository.HorarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HorarioService implements IHorarioService{
    @Autowired
    HorarioRepository repHorario;

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
    public void eliminarHorario(HorarioCurso horario) {
        repHorario.delete(horario);
    }
    
}
