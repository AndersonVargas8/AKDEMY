package com.app.akdemy.dto;

import java.util.Date;

public class Message {
    private Date date;
    private String content;
    private Boolean profesor;


    public Message() {
    }


    public Message(Date date, String content, Boolean isProfesor) {
        this.date = date;
        this.content = content;
        this.profesor = isProfesor;
    }

    public Message(Long date, String content, Boolean isProfesor) {
        this.date = new Date(date);
        this.content = content;
        this.profesor = isProfesor;
    }


    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean isProfesor() {
        return this.profesor;
    }

    public Boolean getProfesor() {
        return this.profesor;
    }

    public void setProfesor(Boolean profesor) {
        this.profesor = profesor;
    }





}
