package com.app.akdemy.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.app.akdemy.entity.Curso;
import com.app.akdemy.entity.MateriaGrado;
import com.app.akdemy.entity.Periodo;
import com.app.akdemy.entity.Profesor;

public class CalificacionDTO {
    private Profesor profesor;
    private Curso curso;
    private MateriaGrado materia;
    private Periodo periodo;
    private List<EstudianteCalificacionDTO> estudiantes;
    private boolean cerrada;

    public CalificacionDTO() {
        this.estudiantes = new ArrayList<EstudianteCalificacionDTO>();
        this.cerrada = false;
    }

    public CalificacionDTO(Profesor profesor, Curso curso, MateriaGrado materia, Periodo periodo, List<EstudianteCalificacionDTO> estudiantes, boolean cerrada) {
        this.profesor = profesor;
        this.curso = curso;
        this.materia = materia;
        this.periodo = periodo;
        this.estudiantes = estudiantes;
        this.cerrada = cerrada;
    }

    public Profesor getProfesor() {
        return this.profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Curso getCurso() {
        return this.curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public MateriaGrado getMateria() {
        return this.materia;
    }

    public void setMateria(MateriaGrado materia) {
        this.materia = materia;
    }

    public Periodo getPeriodo() {
        return this.periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public List<EstudianteCalificacionDTO> getEstudiantes() {
        return this.estudiantes;
    }

    public void setEstudiantes(List<EstudianteCalificacionDTO> estudiantes) {
        this.estudiantes = estudiantes;
    }

    public boolean getCerrada(){
        return this.cerrada;
    }

    public boolean isCerrada(){
        return this.cerrada;
    }

    public void setCerrada(boolean cerrada){
        this.cerrada = cerrada;
    }
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CalificacionDTO)) {
            return false;
        }
        CalificacionDTO calificacionDTO = (CalificacionDTO) o;
        return Objects.equals(profesor, calificacionDTO.profesor) && Objects.equals(curso, calificacionDTO.curso) && Objects.equals(materia, calificacionDTO.materia) && Objects.equals(periodo, calificacionDTO.periodo) && Objects.equals(estudiantes, calificacionDTO.estudiantes) && cerrada == calificacionDTO.cerrada;
    }

    @Override
    public int hashCode() {
        return Objects.hash(profesor, curso, materia, periodo, estudiantes, cerrada);
    }

    @Override
    public String toString() {
        return "{" +
            " profesor='" + getProfesor() + "'" +
            ", curso='" + getCurso() + "'" +
            ", materia='" + getMateria() + "'" +
            ", periodo='" + getPeriodo() + "'" +
            ", estudiantes='" + getEstudiantes() + "'" +
            ", cerrada='" + isCerrada() + "'" +
            "}";
    }
    

}
