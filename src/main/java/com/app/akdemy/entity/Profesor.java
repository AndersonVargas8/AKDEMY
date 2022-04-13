package com.app.akdemy.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Profesor")
public class Profesor {
    
    //Definición de columnas para la tabla Profesor

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id_Profesor;

    @Column(name = "Nombres_Profesor", nullable = false, length = 70)
    private String Nombres_Profesor;

    @Column(name = "Apellidos_Profesor", nullable = false, length = 70)
    private String Apellidos_Profesor;

    @Column(name = "Fecha_Nacimiento_P", nullable = false)
    private Date Fecha_Nacimiento_P;

    //Constructores de la clase Coordinador

    public Profesor() {
    }

    public Profesor(Integer Id_Profesor, String Nombres_Profesor, String Apellidos_Profesor, Date Fecha_Nacimiento_P) {
        this.Id_Profesor = Id_Profesor;
        this.Nombres_Profesor = Nombres_Profesor;
        this.Apellidos_Profesor = Apellidos_Profesor;
        this.Fecha_Nacimiento_P = Fecha_Nacimiento_P;
    }

    public Profesor(Integer Id_Profesor) {
        this.Id_Profesor = Id_Profesor;
    }

    //Relaciones con otras tablas

    @OneToOne(cascade = CascadeType.ALL)
    private Curso curso;

    @OneToOne
    @JoinColumn(name = "Usuario", updatable = false, nullable = false)
    private User usuario;

    @ManyToMany
    @JoinTable(name = "Materia_Profesor"
            , joinColumns = @JoinColumn(name = "Id_Profesor")
            , inverseJoinColumns = @JoinColumn(name = "Id_Materia"))
    private Set<Materia_Grado> materia;

    //Getter y Setter

    public Integer getId_Profesor() {
        return Id_Profesor;
    }

    public void setId_Profesor(Integer Id_Profesor) {
        this.Id_Profesor = Id_Profesor;
    }

    public String getNombres_Profesor() {
        return Nombres_Profesor;
    }

    public void setNombres_Profesor(String Nombres_Profesor) {
        this.Nombres_Profesor = Nombres_Profesor;
    }

    public String getApellidos_Profesor() {
        return Apellidos_Profesor;
    }

    public void setApellidos_Profesor(String Apellidos_Profesor) {
        this.Apellidos_Profesor = Apellidos_Profesor;
    }

    public Date getFecha_Nacimiento_P() {
        return Fecha_Nacimiento_P;
    }

    public void setFecha_Nacimiento_P(Date Fecha_Nacimiento_P) {
        this.Fecha_Nacimiento_P = Fecha_Nacimiento_P;
    }

}