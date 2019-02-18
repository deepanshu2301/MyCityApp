package com.dipanshu.mycityapp;

public class details {

    name name;
    description description;
    start start;
    logo logo;

    public name getName() {
        return name;
    }

    public void setName(name name) {
        this.name = name;
    }

    public description getDescription() {
        return description;
    }

    public void setDescription(description description) {
        this.description = description;
    }

    public start getStart() {
        return start;
    }

    public void setStart(start start) {
        this.start = start;
    }

    public logo getLogo() {
        return logo;
    }

    public void setLogo(logo logo) {
        this.logo = logo;
    }

    public details(name name, description description, start start, logo logo) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.logo = logo;
    }

}
