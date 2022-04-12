package com.app.akdemy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Acudiente")
public class Acudiente {

    //Definición de columnas para la tabla Acudiente

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Acudiente;

    @Column(name = "Nombres_Acudiente", nullable = false, length = 70)
    private String Nombres_Acudiente;

    @Column(name = "Apellidos_Acudiente", nullable = false, length = 70)
    private String Apellidos_Acudiente;

    @Column(name = "Telefono_Acudiente", nullable = false)
    private Long Telefono_Acudiente;

    @Column(name = "Correo_Acudiente", nullable = false, length = 200)
    private String Correo_Acudiente;

    //Getter y setter

    public Integer getId_Acudiente() {
        return Id_Acudiente;
    }

    public void setId_Acudiente(Integer Id_Acudiente) {
        this.Id_Acudiente = Id_Acudiente;
    }

    public String getNombres_Acudiente() {
        return Nombres_Acudiente;
    }

    public void setNombres_Acudiente(String Nombres_Acudiente) {
        this.Nombres_Acudiente = Nombres_Acudiente;
    }

    public String getApellidos_Acudiente() {
        return Apellidos_Acudiente;
    }

    public void setApellidos_Acudiente(String Apellidos_Acudiente) {
        this.Apellidos_Acudiente = Apellidos_Acudiente;
    }

    public Long getTelefono_Acudiente() {
        return Telefono_Acudiente;
    }

    public void setTelefono_Acudiente(Long Telefono_Acudiente) {
        this.Telefono_Acudiente = Telefono_Acudiente;
    }

    public String getCorreo_Acudiente() {
        return Correo_Acudiente;
    }

    public void setCorreo_Acudiente(String Correo_Acudiente) {
        this.Correo_Acudiente= Correo_Acudiente;
    }

}