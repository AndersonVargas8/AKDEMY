package com.app.akdemy.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.app.akdemy.entity.Calificacion;
import com.app.akdemy.entity.MateriaGrado;
import com.app.akdemy.entity.Periodo;
import com.app.akdemy.entity.Profesor;

public class CalificacionesEstDTO {
    private String nombreEstudiante;
    private String curso;
    private Float promedioGeneral;
    private List<MateriaCalificacionDTO> materias;

    private HashMap<Long,Integer> indicesMaterias;
    private HashMap<Long,Integer> indicesPeriodos;


    public CalificacionesEstDTO(List<Periodo> periodos) {
        indicesPeriodos = new HashMap<>();
        int i = 0;
        for(Periodo periodo: periodos){
            indicesPeriodos.put(periodo.getId(), i++);
        }
        this.materias = new ArrayList<>();
        this.indicesMaterias = new HashMap<>();
    }

    public String getNombreEstudiante() {
        return this.nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getCurso() {
        return this.curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Float getPromedioGeneral() {
        if(this.promedioGeneral == null)
            return null;
        return Math.round(this.promedioGeneral*10f)/10f;
    }

    public void setPromedioGeneral(Float promedioGeneral) {
        this.promedioGeneral = promedioGeneral;
    }

    public List<MateriaCalificacionDTO> getMaterias() {
        Collections.sort(this.materias);
        return this.materias;
    }

    public void setMaterias(List<MateriaCalificacionDTO> materias) {
        this.materias = materias;
    }

    public CalificacionesEstDTO nombreEstudiante(String nombreEstudiante) {
        setNombreEstudiante(nombreEstudiante);
        return this;
    }

    public CalificacionesEstDTO curso(String curso) {
        setCurso(curso);
        return this;
    }

    public CalificacionesEstDTO promedioGeneral(Float promedioGeneral) {
        setPromedioGeneral(promedioGeneral);
        return this;
    }

    public CalificacionesEstDTO materias(List<MateriaCalificacionDTO> materias) {
        setMaterias(materias);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CalificacionesEstDTO)) {
            return false;
        }
        CalificacionesEstDTO calificacionesEstDTO = (CalificacionesEstDTO) o;
        return Objects.equals(nombreEstudiante, calificacionesEstDTO.nombreEstudiante) && Objects.equals(curso, calificacionesEstDTO.curso) && Objects.equals(promedioGeneral, calificacionesEstDTO.promedioGeneral) && Objects.equals(materias, calificacionesEstDTO.materias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreEstudiante, curso, promedioGeneral, materias);
    }

    @Override
    public String toString() {
        return "{" +
            " nombreEstudiante='" + getNombreEstudiante() + "'" +
            ", curso='" + getCurso() + "'" +
            ", promedioGeneral='" + getPromedioGeneral() + "'" +
            ", materias='" + getMaterias() + "'" +
            "}";
    }

    public void agregarCalificacion(Calificacion calificacion){
        long idMateria = calificacion.getMateria().getId();
        Integer indiceMat = this.indicesMaterias.get(idMateria);
        MateriaCalificacionDTO materia;
        if(indiceMat == null){
            this.indicesMaterias.put(idMateria, materias.size());
            materia = new MateriaCalificacionDTO(indicesPeriodos.size());
            materia.setNombre(calificacion.getMateria().getMateria().getNombre());
            materia.setNombreProfesor(calificacion.getProfesor().getNombres().concat(" "+calificacion.getProfesor().getApellidos()));
            materias.add(materia);
        }else{
            materia = materias.get(indiceMat);
        }

        int indicePer = indicesPeriodos.get(calificacion.getPeriodo().getId());

        EstudianteCalificacionDTO estCalificacion = new EstudianteCalificacionDTO();
        estCalificacion.setNota(calificacion.getNota());
        estCalificacion.setCerrada(calificacion.isCerrada());

        materia.agregarCalificacion(indicePer, estCalificacion);
        calcularPromedio();
    }

    public void agregarMateria(MateriaGrado materia, Profesor profesor){
        long idMateria = materia.getId();
        Integer indiceMat = this.indicesMaterias.get(idMateria);
        if(indiceMat == null){
            this.indicesMaterias.put(idMateria, materias.size());
            MateriaCalificacionDTO nuevaMateria = new MateriaCalificacionDTO(indicesPeriodos.size());
            nuevaMateria.setNombre(materia.getMateria().getNombre());
            nuevaMateria.setNombreProfesor(profesor.getNombres().concat(" "+profesor.getApellidos()));
            materias.add(nuevaMateria);
        }
        
    }

    private void calcularPromedio(){
      
        Float suma = 0f;
        int numMaterias = 0;
        for(MateriaCalificacionDTO materia: this.materias){
            if(materia.getPromedio() != null){
                suma += materia.getPromedio();
                numMaterias++;
            }
        }

        this.promedioGeneral = suma/numMaterias;
    }
}
