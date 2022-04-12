package com.app.akdemy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Materia")
public class Materia {

    //Definición de columnas para la tabla Materia

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Materia;

    @Column(name = "Nombre_Materia", nullable = false, length = 100)
    private String Nombre_Materia;

    //Getter y Setter

    public Integer getId_Materia() {
        return Id_Materia;
    }

    public void setId_Materia(Integer Id_Materia) {
        this.Id_Materia = Id_Materia;
    }

    public String getNombre_Materia() {
        return Nombre_Materia;
    }

    public void setNombre_Materia(String Nombre_Materia) {
        this.Nombre_Materia= Nombre_Materia;
    }

}