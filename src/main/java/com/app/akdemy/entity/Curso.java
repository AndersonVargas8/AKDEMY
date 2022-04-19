package com.app.akdemy.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    private Integer id;

    @Column(name = "cur_nombre", nullable = false, length = 70)
    private Integer nombre_Curso;

    @Column(name = "cur_anio", nullable = false, length = 70)
    private Integer anio_Curso;

    //Relaciones con otras tablas

    @OneToOne
    @JoinColumn(name = "cur_director", nullable = false)
    private Profesor profesor;

    @ManyToMany
    @JoinTable(name = "curso_estudiante"
            , joinColumns = @JoinColumn(name = "id_estudiante")
            , inverseJoinColumns = @JoinColumn(name = "id_curso"))
    private Set<Estudiante> estudiantes;


    //Constructor clase Curso 


    public Curso() {
    }

    public Curso(int id, Integer nombre_Curso, Integer anio_Curso, Profesor profesor, Set<Estudiante> estudiantes) {
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

    public void setId(int id) {
        this.id = id;
    }

    public Integer getNombre_Curso() {
        return this.nombre_Curso;
    }

    public void setNombre_Curso(Integer nombre_Curso) {
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

    public Set<Estudiante> getEstudiantes() {
        return this.estudiantes;
    }

    public void setEstudiantes(Set<Estudiante> estudiantes) {
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
        return id == curso.id && Objects.equals(nombre_Curso, curso.nombre_Curso) && Objects.equals(anio_Curso, curso.anio_Curso) && Objects.equals(profesor, curso.profesor) && Objects.equals(estudiantes, curso.estudiantes);
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
