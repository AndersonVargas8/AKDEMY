package com.app.akdemy.dto;

import java.util.Date;

public class Message {
    private Date date;
    private String content;


    public Message() {
    }


    public Message(Date date, String content) {
        this.date = date;
        this.content = content;
    }

    public Message(Long date, String content) {
        this.date = new Date(date);
        this.content = content;
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


}
