package com.app.akdemy.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class Curso {

    @Id private long id;

    @Column
    private Integer nombre_Curso;

    @Column
    private Integer anio_Curso;

    @Column 
	private String descripcion;


    public Curso() {
    }

    public Curso(long id, Integer nombre_Curso, Integer anio_Curso, String descripcion) {
        this.id = id;
        this.anio_Curso = anio_Curso;
        this.nombre_Curso = nombre_Curso;
        this.descripcion = descripcion;
    }

    //Relaciones con otras tablas

    @OneToOne
    @JoinColumn(name = "Director_Curso", updatable = false, nullable = false)
    private Profesor profesor;

    @ManyToMany
    @JoinTable(name = "Curso_Estudiante"
            , joinColumns = @JoinColumn(name = "Id_Estudiante")
            , inverseJoinColumns = @JoinColumn(name = "Id_Curso"))
    private Set<Curso> curso;

    //Getter y Setter


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getNombre_Curso() {
        return nombre_Curso;
    }

    public void setNombre_Curso(Integer nombre_Curso) {
        this.nombre_Curso = nombre_Curso;
    }

    public Integer getAnio_Curso() {
        return anio_Curso;
    }

    public void setAnio_Curso(Integer anio_Curso) {
        this.anio_Curso = anio_Curso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Set<Curso> getCurso() {
        return curso;
    }

    public void setCurso(Set<Curso> curso) {
        this.curso = curso;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Curso)) {
            return false;
        }
        Curso curso = (Curso) o;
        return id == curso.id && Objects.equals(descripcion, curso.descripcion) && Objects.equals(anio_Curso, curso.anio_Curso) && Objects.equals(nombre_Curso, curso.nombre_Curso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre_Curso, anio_Curso, descripcion);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre_Curso() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }


}
