package com.app.akdemy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Materia_Grado")
public class Materia_Grado {

    //Definición de columnas para la tabla Materia_Grado

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Materia_Grado;

    @Column(name = "Grado_Materia", nullable = false)
    private Integer Grado_Materia;

    @Column(name = "Descripcion", nullable = false)
    private String Descripcion;

    //Getter y Setter

    public Integer getId_Materia_Grado() {
        return Id_Materia_Grado;
    }

    public void setId_Materia_Grado(Integer Id_Materia_Grado) {
        this.Id_Materia_Grado = Id_Materia_Grado;
    }

    public Integer getGrado_Materia() {
        return Grado_Materia;
    }

    public void setGrado_Materia(Integer Grado_Materia) {
        this.Grado_Materia = Grado_Materia;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
}