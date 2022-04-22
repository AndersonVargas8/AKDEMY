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
@Table(name = "coordinador")
public class Coordinador {

    //Definición de columnas para la tabla Coordinador

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "coo_nombres", nullable = false, length = 70)
    private String nombres;

    @Column(name = "coo_apellidos", nullable = false, length = 70)
    private String apeliidos;

    @Column(name = "coo_fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "coo_documento", nullable = false)
    private String documento;

    @OneToOne
    @JoinColumn(name = "coo_usuario", updatable = false, nullable = false)
    private User usuario;

    //Constructores de la clase Coordinador

    public Coordinador() {
    }



    public Coordinador(long id, String nombres, String apeliidos, Date fechaNacimiento, String documento, User usuario) {
        this.id = id;
        this.nombres = nombres;
        this.apeliidos = apeliidos;
        this.fechaNacimiento = fechaNacimiento;
        this.documento = documento;
        this.usuario = usuario;
    }

   
    //Getter y Setter
    
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApeliidos() {
        return this.apeliidos;
    }

    public void setApeliidos(String apeliidos) {
        this.apeliidos = apeliidos;
    }

    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

     public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
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
        if (!(o instanceof Coordinador)) {
            return false;
        }
        Coordinador coordinador = (Coordinador) o;
        return Objects.equals(id, coordinador.id) && Objects.equals(nombres, coordinador.nombres) && Objects.equals(apeliidos, coordinador.apeliidos) && Objects.equals(fechaNacimiento, coordinador.fechaNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombres, apeliidos, fechaNacimiento);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombres='" + getNombres() + "'" +
            ", apeliidos='" + getApeliidos() + "'" +
            ", fechaNacimiento='" + getFechaNacimiento() + "'" +
            "}";
    }
    
    
}
