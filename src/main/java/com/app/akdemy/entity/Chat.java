package com.app.akdemy.entity;

import java.util.Date;

public class Chat {

    private String id;
    private Acudiente acudiente;
    private Profesor profesor;
    private Estudiante estudiante;
    private Date lastUpdate;


    //Constructores de la clase Chat

    public Chat() {
    }

    public Chat(Acudiente acudiente, Profesor profesor, Estudiante estudiante, Date lastUpdate) {
        this.acudiente = acudiente;
        this.profesor = profesor;
        this.estudiante = estudiante;
        this.lastUpdate = lastUpdate;
    }

    public Chat(String id, Acudiente acudiente, Profesor profesor, Estudiante estudiante, Long lastUpdate) {
        this.id = id;
        this.acudiente = acudiente;
        this.profesor = profesor;
        this.estudiante = estudiante;
        this.lastUpdate = new Date(lastUpdate);
    }


    //Getters y Setters
    
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Acudiente getAcudiente() {
        return this.acudiente;
    }

    public void setAcudiente(Acudiente acudiente) {
        this.acudiente = acudiente;
    }

    public Profesor getProfesor() {
        return this.profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Estudiante getEstudiante() {
        return this.estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Date getLastUpdate() {
        return this.lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }


}
