package com.app.akdemy.entity;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;


@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cur_nombre", nullable = false, length = 70)
    private String nombre_Curso;

    @Column(name = "cur_anio", nullable = false, length = 70)
    private Integer anio_Curso;

    //Relaciones con otras tablas

    @OneToOne
    @JoinColumn(name = "cur_director", nullable = false)
    private Profesor profesor;

    @ManyToMany
    @JoinTable(name = "curso_estudiante"
            , joinColumns = @JoinColumn(name = "id_curso")
            , inverseJoinColumns = @JoinColumn(name = "id_estudiante"))
    private List<Estudiante> estudiantes;


    //Constructor clase Curso 

    public Curso() {
    }

    public Curso(long id, String nombre_Curso, Integer anio_Curso, Profesor profesor, List<Estudiante> estudiantes) {
        this.id = id;
        this.nombre_Curso = nombre_Curso;
        this.anio_Curso = anio_Curso;
        this.profesor = profesor;
        this.estudiantes = estudiantes;
    }

    //Getter y Setter
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre_Curso() {
        return this.nombre_Curso;
    }

    public void setNombre_Curso(String nombre_Curso) {
        this.nombre_Curso = nombre_Curso;
    }

    public Integer getAnio_Curso() {
        return this.anio_Curso;
    }

    public void setAnio_Curso(Integer anio_Curso) {
        this.anio_Curso = anio_Curso;
    }

    public Profesor getProfesor() {
        return this.profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public List<Estudiante> getEstudiantes() {
        return this.estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Curso)) {
            return false;
        }
        Curso curso = (Curso) o;
        return id == curso.id && Objects.equals(nombre_Curso, curso.nombre_Curso)
                && Objects.equals(anio_Curso, curso.anio_Curso) && Objects.equals(profesor, curso.profesor) && Objects.equals(estudiantes, curso.estudiantes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre_Curso, anio_Curso, profesor, estudiantes);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre_Curso='" + getNombre_Curso() + "'" +
            ", anio_Curso='" + getAnio_Curso() + "'" +
            ", profesor='" + getProfesor() + "'" +
            ", estudiantes='" + getEstudiantes() + "'" +
            "}";
    }

}
