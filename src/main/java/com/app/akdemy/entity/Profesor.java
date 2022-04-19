package com.app.akdemy.entity;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "profesor")
public class Profesor {
    
    //Definición de columnas para la tabla Profesor

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "pro_nombres", nullable = false, length = 70)
    private String nombres;

    @Column(name = "pro_apellidos", nullable = false, length = 70)
    private String apellidos;

    @Column(name = "pro_documento")
    private String documento;

    @Column(name = "pro_fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @OneToOne
    @JoinColumn(name = "pro_usuario", updatable = false, nullable = false)
    private User usuario;


    //Constructores de la clase Coordinador

    public Profesor() {
    }



    public Profesor(Integer id, String nombres, String apellidos, String documento, Date fechaNacimiento, User usuario) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.fechaNacimiento = fechaNacimiento;
        this.usuario = usuario;
    }

   
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public User getUsuario() {
        return this.usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
   


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Profesor)) {
            return false;
        }
        Profesor profesor = (Profesor) o;
        return Objects.equals(id, profesor.id) && Objects.equals(nombres, profesor.nombres) && Objects.equals(apellidos, profesor.apellidos) && Objects.equals(documento, profesor.documento) && Objects.equals(fechaNacimiento, profesor.fechaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombres, apellidos, documento, fechaNacimiento);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombres='" + getNombres() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            ", documento='" + getDocumento() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            "}";
    }
    

}