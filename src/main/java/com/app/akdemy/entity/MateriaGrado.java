package com.app.akdemy.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "materia_grado")
public class MateriaGrado {

    //Definición de columnas para la tabla Materia_Grado

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    
    @Column(name = "mat_gra_grado", nullable = false)
    private Integer grado;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    //Relaciones con otras tablas  

    @ManyToOne
    @JoinColumn(name= "id_Materia")
    private Materia materia;


    public MateriaGrado() {
    }

    public MateriaGrado(Integer id, Integer grado, String descripcion, Materia materia) {
        this.id = id;
        this.grado = grado;
        this.descripcion = descripcion;
        this.materia = materia;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrado() {
        return this.grado;
    }

    public void setGrado(Integer grado) {
        this.grado = grado;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Materia getMateria() {
        return this.materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MateriaGrado)) {
            return false;
        }
        MateriaGrado materiaGrado = (MateriaGrado) o;
        return Objects.equals(id, materiaGrado.id) && Objects.equals(grado, materiaGrado.grado) && Objects.equals(descripcion, materiaGrado.descripcion) && Objects.equals(materia, materiaGrado.materia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grado, descripcion, materia);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", grado='" + getGrado() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", materia='" + getMateria() + "'" +
            "}";
    }
    
    
}