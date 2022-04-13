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

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @Column
    private String Nombre_Curso;

    @Column
    private Integer Anio_Curso;

    @Column 
	private String descripcion;


    public Curso() {
    }

    public Curso(long id, String Nombre_Curso, Integer Anio_Curso, String descripcion) {
        this.id = id;
        this.Anio_Curso = Anio_Curso;
        this.Nombre_Curso = Nombre_Curso;
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
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre_Curso() {
        return this.Nombre_Curso;
    }

    public void setNombre_Curso(String Nombre_Curso) {
        this.Nombre_Curso = Nombre_Curso;
    }

    public Integer getAnio_Curso() {
        return this.Anio_Curso;
    }

    public void setAnio_Curso(Integer Anio_Curso) {
        this.Anio_Curso= Anio_Curso;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Curso)) {
            return false;
        }
        Curso curso = (Curso) o;
        return id == curso.id && Objects.equals(descripcion, curso.descripcion) && Objects.equals(Anio_Curso, curso.Anio_Curso) && Objects.equals(Nombre_Curso, curso.Nombre_Curso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Nombre_Curso, Anio_Curso, descripcion);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getDescripcion() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }


}
