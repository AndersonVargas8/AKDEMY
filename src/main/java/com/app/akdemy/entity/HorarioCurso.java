package com.app.akdemy.entity;

import java.sql.Time;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "horario_curso")
public class HorarioCurso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hor_cur_curso")
    private Curso curso;    

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hor_cur_profesor")
    private Profesor profesor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hor_cur_materia")
    private MateriaGrado materia;

    @Column(name = "hor_cur_dia")
    private String dia;

    @Column(name = "hor_cur_hora_inicio")
    private Time horaInicio;

    @Column(name = "hor_cur_hora_fin")
    private Time horaFin;

    //Constructores de la clase Coordinador

    public HorarioCurso() {
    }



    public HorarioCurso(long id, Curso curso, Profesor profesor, MateriaGrado materia, String dia, Time horaInicio, Time horaFin) {
        this.id = id;
        this.curso = curso;
        this.profesor = profesor;
        this.materia = materia;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    //Getter y Setter

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Curso getCurso() {
        return this.curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Profesor getProfesor() {
        return this.profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public MateriaGrado getMateria() {
        return this.materia;
    }

    public void setMateria(MateriaGrado materia) {
        this.materia = materia;
    }

    public String getDia() {
        return this.dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Time getHoraInicio() {
        return this.horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFin() {
        return this.horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof HorarioCurso)) {
            return false;
        }
        HorarioCurso horarioCurso = (HorarioCurso) o;
        return id == horarioCurso.id && Objects.equals(curso, horarioCurso.curso) && Objects.equals(profesor, horarioCurso.profesor) && Objects.equals(materia, horarioCurso.materia) && Objects.equals(dia, horarioCurso.dia) && Objects.equals(horaInicio, horarioCurso.horaInicio) && Objects.equals(horaFin, horarioCurso.horaFin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, curso, profesor, materia, dia, horaInicio, horaFin);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", curso='" + getCurso() + "'" +
            ", profesor='" + getProfesor() + "'" +
            ", materia='" + getMateria() + "'" +
            ", dia='" + getDia() + "'" +
            ", horaInicio='" + getHoraInicio() + "'" +
            ", horaFin='" + getHoraFin() + "'" +
            "}";
    }
    
}
