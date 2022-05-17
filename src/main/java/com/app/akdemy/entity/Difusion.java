package com.app.akdemy.entity;

import java.util.Date;

public class Difusion {

    private Curso curso;
    private Profesor profesor;
    private Date date;
    private String subject;
    private String message;

    //Constructor de la clase Difusion


    public Difusion() {
    }


    public Difusion(Curso curso, Profesor profesor, Date date, String subject, String message) {
        this.curso = curso;
        this.profesor = profesor;
        this.date = date;
        this.subject = subject;
        this.message = message;
    }



    //Getters y Setters
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

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
