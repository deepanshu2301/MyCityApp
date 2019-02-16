package com.dipanshu.mycityapp;

public class Article {

    private String city,message;

    public Article() {
    }

    public Article(String city, String message) {
        this.city = city;
        this.message = message;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
