package com.app.akdemy.entity;

import java.sql.Date;
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
@Table(name = "observador")
public class Observador {

     //Definición de columnas para la tabla Observador

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
 
     @Column(name = "obs_descargos", nullable = false)
     private String descargos;
 
     @Column(name = "obs_fecha", nullable = false)
     private Date fecha;

    @ManyToOne
    @JoinColumn(name = "obs_estudiante")
    Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "obs_profesor")
    Profesor profesor;

     //Constructores de la calse Observador

    public Observador() {
    }


    public Observador(Integer id, String descargos, Date fecha, Estudiante estudiante, Profesor profesor) {
        this.id = id;
        this.descargos = descargos;
        this.fecha = fecha;
        this.estudiante = estudiante;
        this.profesor = profesor;
    }

    //Getter y Setter
    
    public Estudiante getEstudiante() {
        return this.estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Profesor getProfesor() {
        return this.profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
  

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescargos() {
        return this.descargos;
    }

    public void setDescargos(String descargos) {
        this.descargos = descargos;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Observador)) {
            return false;
        }
        Observador observador = (Observador) o;
        return Objects.equals(id, observador.id) && Objects.equals(descargos, observador.descargos) && Objects.equals(fecha, observador.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descargos, fecha);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", descargos='" + getDescargos() + "'" +
            ", fecha='" + getFecha() + "'" +
            "}";
    }
    
}
