package com.app.akdemy.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @Column 
	private String nombre;

    @Column 
	private int tipoDocumento;

    @Column 
	private String documento;

    //Relaciones con otras tablas

    @ManyToOne(optional = false, cascade = CascadeType.ALL,  fetch= FetchType.EAGER)
    private Curso curso;

    @OneToOne(cascade = CascadeType.ALL)
    private Observador observador;

    @OneToOne
    @JoinColumn(name = "Usuario", updatable = false, nullable = false)
    private User usuario;

    @ManyToMany
    @JoinTable(name = "Acudiente_Estudiante"
            , joinColumns = @JoinColumn(name = "Id_Estudiante")
            , inverseJoinColumns = @JoinColumn(name = "Id_Acudiente"))
    private Set<Acudiente> acudiente;


    public Estudiante() {
    }

    public Estudiante(long id, String nombre, int tipoDocumento, String documento, Curso curso) {
        this.id = id;
        this.nombre = nombre;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.curso = curso;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTipoDocumento() {
        return this.tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Curso getCurso() {
        return this.curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Estudiante)) {
            return false;
        }
        Estudiante estudiante = (Estudiante) o;
        return id == estudiante.id && Objects.equals(nombre, estudiante.nombre) && tipoDocumento == estudiante.tipoDocumento && Objects.equals(documento, estudiante.documento) && Objects.equals(curso, estudiante.curso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, tipoDocumento, documento, curso);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", tipoDocumento='" + getTipoDocumento() + "'" +
            ", documento='" + getDocumento() + "'" +
            ", curso='" + getCurso() + "'" +
            "}";
    }


}
