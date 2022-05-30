package com.app.akdemy.dto;

import java.util.Objects;

import com.app.akdemy.entity.Estudiante;

public class EstudianteCalificacionDTO {
    private long id;
    private String nombres;
    private String apellidos;
    private Float nota;
    private boolean cerrada;
    private long idCalificacion;


    public EstudianteCalificacionDTO() {
    }

    public EstudianteCalificacionDTO(long id, String nombres, String apellidos, Float nota, boolean cerrada, long idCalificacion) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nota = nota;
        this.cerrada = cerrada;
        this.idCalificacion = idCalificacion;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public long getIdCalificacion(){
        return this.idCalificacion;
    }

    public void setIdCalificacion(long idCalificacion){
        this.idCalificacion = idCalificacion;
    }
    
    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Float getNota() {
        return this.nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public boolean isCerrada() {
        return this.cerrada;
    }

    public boolean getCerrada() {
        return this.cerrada;
    }

    public void setCerrada(boolean cerrada) {
        this.cerrada = cerrada;
    }

    public void mapEstudiante(Estudiante estudiante){
        this.id = estudiante.getId();
        this.nombres = estudiante.getNombres();
        this.apellidos = estudiante.getApellidos();
        this.nota = null;
        this.cerrada = false;
        this.idCalificacion = 0;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EstudianteCalificacionDTO)) {
            return false;
        }
        EstudianteCalificacionDTO estudianteCalificacionDTO = (EstudianteCalificacionDTO) o;
        return Objects.equals(nombres, estudianteCalificacionDTO.nombres) && Objects.equals(apellidos, estudianteCalificacionDTO.apellidos) && Objects.equals(nota, estudianteCalificacionDTO.nota) && cerrada == estudianteCalificacionDTO.cerrada;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombres, apellidos, nota, cerrada);
    }

    @Override
    public String toString() {
        return "{" +
            " nombres='" + getNombres() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            ", nota='" + getNota() + "'" +
            ", cerrada='" + isCerrada() + "'" +
            "}";
    }

}
