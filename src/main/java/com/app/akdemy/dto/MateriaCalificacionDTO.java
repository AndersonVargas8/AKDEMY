package com.app.akdemy.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MateriaCalificacionDTO implements Comparable<MateriaCalificacionDTO>{
    private String nombre;
    private String nombreProfesor;
    private Float promedio;
    private List<EstudianteCalificacionDTO> calificaciones;

    public MateriaCalificacionDTO(int numPeriodos) {
        this.calificaciones = new ArrayList<>();
        for(int i = 0; i < numPeriodos; i++){
            calificaciones.add(null);
        }
    }

    public MateriaCalificacionDTO(String nombre, String nombreProfesor, Float promedio, List<EstudianteCalificacionDTO> calificaciones) {
        this.nombre = nombre;
        this.nombreProfesor = nombreProfesor;
        this.promedio = promedio;
        this.calificaciones = calificaciones;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreProfesor() {
        return this.nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public Float getPromedio() {
        if(this.promedio == null)
            return null;
        return Math.round(this.promedio*10f)/10f;
    }

    public void setPromedio(Float promedio) {
        this.promedio = promedio;
    }

    public List<EstudianteCalificacionDTO> getCalificaciones() {
        return this.calificaciones;
    }

    public void setCalificaciones(List<EstudianteCalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public MateriaCalificacionDTO nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public MateriaCalificacionDTO nombreProfesor(String nombreProfesor) {
        setNombreProfesor(nombreProfesor);
        return this;
    }

    public MateriaCalificacionDTO promedio(Float promedio) {
        setPromedio(promedio);
        return this;
    }

    public MateriaCalificacionDTO calificaciones(List<EstudianteCalificacionDTO> calificaciones) {
        setCalificaciones(calificaciones);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MateriaCalificacionDTO)) {
            return false;
        }
        MateriaCalificacionDTO materiaCalificacionDTO = (MateriaCalificacionDTO) o;
        return Objects.equals(nombre, materiaCalificacionDTO.nombre) && Objects.equals(nombreProfesor, materiaCalificacionDTO.nombreProfesor) && Objects.equals(promedio, materiaCalificacionDTO.promedio) && Objects.equals(calificaciones, materiaCalificacionDTO.calificaciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, nombreProfesor, promedio, calificaciones);
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", nombreProfesor='" + getNombreProfesor() + "'" +
            ", promedio='" + getPromedio() + "'" +
            ", calificaciones='" + getCalificaciones() + "'" +
            "}";
    }

    public void agregarCalificacion(int index, EstudianteCalificacionDTO calificacion){
        this.calcularNuevoPromedio(calificacion.getNota()); 
        this.calificaciones.set(index, calificacion);
    }

    private void calcularNuevoPromedio(Float nuevaNota){
        if(this.promedio == null)
            this.promedio = 0f;

        int numCalificaciones = 0;
        for(EstudianteCalificacionDTO cal: this.calificaciones){
            if(cal != null)
                numCalificaciones++;
        }
        Float aux = (this.promedio * numCalificaciones);
        
        this.promedio = (aux + nuevaNota)/(numCalificaciones+1);
    }

    @Override
    public int compareTo(MateriaCalificacionDTO materia){
        return this.nombre.compareTo(materia.nombre);
    }

}
