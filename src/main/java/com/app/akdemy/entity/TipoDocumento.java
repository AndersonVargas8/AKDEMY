package com.app.akdemy.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_documento")
public class TipoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_doc_descripcion", nullable = false, length = 70)
    private String descripcion;

    //Constructor
    public TipoDocumento() {
    }

    public TipoDocumento(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoDocumento(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    //Getter y Setter
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Override
    public boolean equals(Object o) {
        if (o == this)
        return true;
        if (!(o instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento tipoDocumento = (TipoDocumento) o;
        return Objects.equals(id, tipoDocumento.id) && Objects.equals(descripcion, tipoDocumento.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", descripcion='" + getDescripcion() + "'" +
                "}";
    }

}
