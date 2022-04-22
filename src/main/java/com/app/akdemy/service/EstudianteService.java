package com.app.akdemy.service;

import java.util.List;

import com.app.akdemy.entity.Acudiente;
import com.app.akdemy.entity.Estudiante;
import com.app.akdemy.interfacesServices.IEstudianteService;
import com.app.akdemy.repository.EstudianteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudianteService implements IEstudianteService {
    @Autowired
    EstudianteRepository repEstudiante;

    @Override
    public void guardarEstudiante(Estudiante estudiante) {
        String nombre = estudiante.getNombres();
        String apellido = estudiante.getApellidos();
        String documento = estudiante.getDocumento();
        String tipD = estudiante.getTipoDocumento().getDescripcion();
        String user = estudiante.getUsuario().getUsername();
        String pass = estudiante.getUsuario().getPassword();
        int rol = estudiante.getUsuario().getRoles().size();
        for(Acudiente ac: estudiante.getAcudientes()){
            String nAc = ac.getNombres();
            int role = ac.getUsuario().getRoles().size();
            String use = ac.getUsuario().getUsername(); 
            pass = ac.getUsuario().getPassword();
        }
        repEstudiante.save(estudiante);

    }

    @Override
    public List<Estudiante> listarEstudiantes() {
        return (List<Estudiante>) repEstudiante.findAll();
    }

    @Override
    public void eliminarEstudiante(Estudiante estudiante) {
        repEstudiante.delete(estudiante);

    }

}
