package com.app.akdemy.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Coordinador")
public class Coordinador {

    //Definición de columnas para la tabla Coordinador

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Coordinador;

    @Column(name = "Nombres_Coordinador", nullable = false, length = 70)
    private String Nombres_Coordinador;

    @Column(name = "Apellidos_Coordinador", nullable = false, length = 70)
    private String Apellidos_Coordinador;

    @Column(name = "Fecha_Nacimiento_C", nullable = false)
    private Date Fecha_Nacimiento_C;

    //Constructores de la clase Coordinador

    public Coordinador() {
    }

    public Coordinador(Integer Id_Coordinador, String Nombres_Coordinador, String Apellidos_Coordinador, Date Fecha_Nacimiento_C) {
        this.Id_Coordinador = Id_Coordinador;
        this.Nombres_Coordinador = Nombres_Coordinador;
        this.Apellidos_Coordinador = Apellidos_Coordinador;
        this.Fecha_Nacimiento_C = Fecha_Nacimiento_C;
    }

    public Coordinador(Integer Id_Coordinador) {
        this.Id_Coordinador = Id_Coordinador;
    }

    //Relaciones con otras tablas

    @OneToOne
    @JoinColumn(name = "Usuario", updatable = false, nullable = false)
    private User usuario;

    //Getter y Setter

    public Integer getId_Coordinador() {
        return Id_Coordinador;
    }

    public void setId_Coordinador(Integer Id_Coordinador) {
        this.Id_Coordinador = Id_Coordinador;
    }

    public String getNombres_Coordinador() {
        return Nombres_Coordinador;
    }

    public void setNombres_Coordinador(String Nombres_Coordinador) {
        this.Nombres_Coordinador = Nombres_Coordinador;
    }

    public String getApellidos_Coordinador() {
        return Apellidos_Coordinador;
    }

    public void setApellidos_Coordinador(String Apellidos_Coordinador) {
        this.Apellidos_Coordinador = Apellidos_Coordinador;
    }

    public Date getFecha_Nacimiento_C() {
        return Fecha_Nacimiento_C;
    }

    public void setFecha_Nacimiento_C(Date Fecha_Nacimiento_C) {
        this.Fecha_Nacimiento_C = Fecha_Nacimiento_C;
    }
    
}
