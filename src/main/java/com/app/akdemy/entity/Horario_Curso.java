package com.app.akdemy.entity;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Horario_Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long Id_Curso;

    @Column
    private String Profesor_HC;

    @Column
    private String Materia_HC;

    @Column
    private String Dia_HC;

    @Column
    private Time Hora_Inicio_HC;

    @Column
    private Time Hora_Fin_HC;

    //Constructores de la clase Coordinador

    public Horario_Curso() {
    }

    public Horario_Curso(long Id_Curso, String Profesor_HC, String Materia_HC, String Dia_HC, Time Hora_Inicio_Hc, Time Hora_Fin_Hc) {
        this.Id_Curso = Id_Curso;
        this.Profesor_HC = Profesor_HC;
        this.Materia_HC = Materia_HC;
        this.Dia_HC = Dia_HC;
        this.Hora_Inicio_HC = Hora_Fin_Hc;
        this.Hora_Fin_HC = Hora_Fin_Hc;
    }

    //Relaciones con otras tablas
/*
    @ManyToOne
    @JoinColumn(name= "Profesor")
    private Curso  curso;
*/
    //Getter y setter

    public long getId_Curso() {
        return this.Id_Curso;
    }

    public void setId_Curso(long Id_Curso) {
        this.Id_Curso = Id_Curso;
    }

    public String Profesor_HC() {
        return this.Profesor_HC();
    }

    public void setProfesor_HC(String Profesor_HC) {
        this.Profesor_HC = Profesor_HC;
    }

    public String Materia_HC() {
        return this.Materia_HC();
    }

    public void setMateria_HC(String Materia_HC) {
        this.Materia_HC = Materia_HC;
    }

    public String Dia_HC() {
        return this.Dia_HC();
    }

    public void setDia_HC(String Dia_HC) {
        this.Dia_HC = Dia_HC;
    }

    public Time Hora_Inicio_HC() {
        return this.Hora_Inicio_HC();
    }

    public void setHora_Inicio_HC(Time Hora_Inicio_HC) {
        this.Hora_Inicio_HC = Hora_Inicio_HC;
    }

    public Time Hora_Fin_HC() {
        return this.Hora_Fin_HC();
    }

    public void setHora_Fin_HC(Time Hora_Fin_HC) {
        this.Hora_Fin_HC = Hora_Fin_HC;
    }


}
