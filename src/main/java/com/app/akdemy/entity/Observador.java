package com.app.akdemy.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Observador")
public class Observador {

     //Definición de columnas para la tabla Observador

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer Id_Observador;
 
     @Column(name = "Descargos", nullable = false)
     private String Descargos;
 
     @Column(name = "Fecha_Observacion", nullable = false)
     private Date Fecha_Observacion;
 
     //Getter y Setter
 
     public Integer getId_Observador() {
         return Id_Observador;
     }
 
     public void setId_Observador(Integer Id_Observador) {
         this.Id_Observador = Id_Observador;
     }
 
     public String getDescargos() {
         return Descargos;
     }
 
     public void setDescargos(String Descargos) {
         this.Descargos = Descargos;
     }
 
     public Date getFecha_Observacion() {
         return Fecha_Observacion;
     }
 
     public void setFecha_Observacion(Date Fecha_Observacion) {
         this.Fecha_Observacion = Fecha_Observacion;
     }

}
