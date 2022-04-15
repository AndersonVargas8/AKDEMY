package com.app.akdemy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Periodo")
public class Periodo {
    
     //Definición de columnas para la tabla Periodo

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer Id_Periodo;

     //Getter y Setter

    public Integer getId_Periodo() {
        return Id_Periodo;
    }

    public void setId_Periodo(Integer Id_Periodo) {
        this.Id_Periodo= Id_Periodo;
    }

}