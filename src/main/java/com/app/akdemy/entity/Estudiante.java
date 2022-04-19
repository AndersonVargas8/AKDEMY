package com.app.akdemy.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    private Integer id;

    @Column(name = "est_nombres", nullable = false, length = 70) 
	private String nombres;

    @Column(name = "est_apellidos", nullable = false, length = 70) 
	private String apellidos;

    @ManyToOne
    @JoinColumn(name = "est_tipo_doc")
    private TipoDocumento tipoDocumento;

    @Column(name = "est_documento")
	private String documento;

    //Relaciones con otras tablas

    @OneToOne
    @JoinColumn(name = "est_usuario", updatable = false, nullable = false)
    private User usuario;

    @ManyToMany
    @JoinTable(name = "acudiente_estudiante"
            , joinColumns = @JoinColumn(name = "id_estudiante")
            , inverseJoinColumns = @JoinColumn(name = "id_acudiente"))
    private Set<Acudiente> acudientes;

    //Constructor

    public Estudiante() {
    }

    public Estudiante(Integer id, String nombres, String apellidos, TipoDocumento tipoDocumento, String documento, User usuario, Set<Acudiente> acudientes) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.usuario = usuario;
        this.acudientes = acudientes;
    }

    //Getter y Setter

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

    public TipoDocumento getTipoDocumento() {
        return this.tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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

    public Set<Acudiente> getAcudientes() {
        return this.acudientes;
    }

    public void setAcudientes(Set<Acudiente> acudientes) {
        this.acudientes = acudientes;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Estudiante)) {
            return false;
        }
        Estudiante estudiante = (Estudiante) o;
        return Objects.equals(id, estudiante.id) && Objects.equals(nombres, estudiante.nombres) && Objects.equals(apellidos, estudiante.apellidos) && Objects.equals(tipoDocumento, estudiante.tipoDocumento) && Objects.equals(documento, estudiante.documento) && Objects.equals(usuario, estudiante.usuario) && Objects.equals(acudientes, estudiante.acudientes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombres, apellidos, tipoDocumento, documento, usuario, acudientes);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nombres='" + getNombres() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            ", tipoDocumento='" + getTipoDocumento() + "'" +
            ", documento='" + getDocumento() + "'" +
            ", usuario='" + getUsuario() + "'" +
            ", acudientes='" + getAcudientes() + "'" +
            "}";
    }
    

}
