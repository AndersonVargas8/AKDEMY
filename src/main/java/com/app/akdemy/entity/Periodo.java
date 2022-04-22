package com.app.akdemy.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "periodo")
public class Periodo {
    
     //Definición de columnas para la tabla Periodo

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;

     @Column(name = "per_descripcion")
     private String descripcion;

    //Constructor
    public Periodo() {
    }

    public Periodo(long id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    //Getter y Setter

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
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
        if (!(o instanceof Periodo)) {
            return false;
        }
        Periodo periodo = (Periodo) o;
        return Objects.equals(id, periodo.id) && Objects.equals(descripcion, periodo.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            "}";
    }

}