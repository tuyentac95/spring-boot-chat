package com.codegym.model;

public class Message {
    private String message;
    private String from;

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
