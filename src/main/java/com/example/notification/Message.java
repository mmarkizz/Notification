package com.example.notification;

public class Message {
    private String text;

    private String to;

    public void setText(String text){
        this.text=text;
    }

    public String getText() {
        return text;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
