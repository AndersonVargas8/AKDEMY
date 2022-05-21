package com.app.akdemy.entity;

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
@Table(name = "Acudiente")
public class Acudiente {

    //Definición de columnas para la tabla Acudiente

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "acu_nombres", nullable = false, length = 70)
    private String nombres;

    @Column(name = "acu_apellidos", nullable = false, length = 70)
    private String apellidos;

    @Column(name = "acu_documento", nullable = false)
    private String documento;

    @Column(name = "acu_telefono")
    private String telefono;

    @Column(name = "acu_correo", length = 200)
    private String correo;


    //Relaciones con otras tablas

    @OneToOne
    @JoinColumn(name = "acu_usuario", updatable = false, nullable = false)
    private User usuario;

    //Constructores de la clase Coordinador

    public Acudiente() {
    }



    public Acudiente(long id, String nombres, String apellidos, String documento, String telefono, String correo, User usuario) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.telefono = telefono;
        this.correo = correo;
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

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public User getUsuario() {
        return this.usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
    
    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Acudiente)) {
            return false;
        }
        Acudiente acudiente = (Acudiente) o;
        return Objects.equals(id, acudiente.id) && Objects.equals(nombres, acudiente.nombres) && Objects.equals(apellidos, acudiente.apellidos) && Objects.equals(telefono, acudiente.telefono) && Objects.equals(correo, acudiente.correo) && Objects.equals(usuario, acudiente.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombres, apellidos, telefono, correo, usuario);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombres='" + getNombres() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", correo='" + getCorreo() + "'" +
            ", usuario='" + getUsuario() + "'" +
            "}";
    }
    
}