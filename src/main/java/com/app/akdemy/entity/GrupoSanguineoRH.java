package com.app.akdemy.entity;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grupo_sanguineo_rh")
public class GrupoSanguineoRH {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "gsrh_descripcion", nullable = false, length = 70)
    private String descripcion;


    public GrupoSanguineoRH() {
    }

    public GrupoSanguineoRH(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

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
        if (!(o instanceof GrupoSanguineoRH)) {
            return false;
        }
        GrupoSanguineoRH grupoSanguineoRH = (GrupoSanguineoRH) o;
        return Objects.equals(id, grupoSanguineoRH.id) && Objects.equals(descripcion, grupoSanguineoRH.descripcion);
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
