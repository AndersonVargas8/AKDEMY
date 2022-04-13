package com.app.akdemy.entity;

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
    private Integer Id_Calificacion;

    @Column(name = "Nota", nullable = false)
    private Float Nota;

    //Constructores de la calse Calificacion

    public Calificacion() {
    }

    public Calificacion(Integer Id_Calificacion, Float Nota) {
        this.Id_Calificacion = Id_Calificacion;
        this.Nota = Nota;
    }

    public Calificacion(Integer Id_Calificacion) {
        this.Id_Calificacion = Id_Calificacion;
    }

    //Relaciones con otras tablas

    @ManyToOne
    @JoinColumn(name = "Periodo")
    private Periodo Periodo;

    @ManyToOne
    @JoinColumn(name = "Estudiante")
    private Estudiante Estudiante;

    //Getter y Setter

   public Integer getId_Calificacion() {
       return Id_Calificacion;
   }

   public void setId_Calificacion(Integer Id_Calificacion) {
       this.Id_Calificacion = Id_Calificacion;
   }

   public Float getNota() {
       return Nota;
   }

   public void setNota(Float Nota) {
       this.Nota= Nota;
   }
    
}