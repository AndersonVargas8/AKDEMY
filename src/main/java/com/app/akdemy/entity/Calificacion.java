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
@Table(name = "calificacion")
public class Calificacion {

    //Definición de columnas para la tabla Calificacion

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cal_nota", nullable = false)
    private Float nota;

    //Relaciones con otras tablas

    @ManyToOne
    @JoinColumn(name = "cal_periodo")
    private Periodo periodo;

    @ManyToOne
    @JoinColumn(name = "cal_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "cal_profesor")
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "cal_materia")
    private MateriaGrado materia;

    @Column(name = "cal_cerrada")
    private boolean cerrada;

    //Constructores de la clase Calificacion    
    public Calificacion() {
    }

    public Calificacion(long id, Float nota, Periodo periodo, Estudiante estudiante, Profesor profesor, MateriaGrado materia, boolean cerrada) {
        this.id = id;
        this.nota = nota;
        this.periodo = periodo;
        this.estudiante = estudiante;
        this.profesor = profesor;
        this.materia = materia;
        this.cerrada = cerrada;
    }

    //Getter y Setter
    
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getNota() {
        return this.nota;
    }

    public void setNota(Float nota) {
        this.nota = nota;
    }

    public Periodo getPeriodo() {
        return this.periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

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

    public MateriaGrado getMateria() {
        return this.materia;
    }

    public void setMateria(MateriaGrado materia) {
        this.materia = materia;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Calificacion)) {
            return false;
        }
        Calificacion calificacion = (Calificacion) o;
        return Objects.equals(id, calificacion.id) && Objects.equals(nota, calificacion.nota) && Objects.equals(periodo, calificacion.periodo) && Objects.equals(estudiante, calificacion.estudiante) && Objects.equals(profesor, calificacion.profesor) && Objects.equals(materia, calificacion.materia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nota, periodo, estudiante, profesor, materia);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nota='" + getNota() + "'" +
            ", periodo='" + getPeriodo() + "'" +
            ", estudiante='" + getEstudiante() + "'" +
            ", profesor='" + getProfesor() + "'" +
            ", materia='" + getMateria() + "'" +
            "}";
    }
    
    
}