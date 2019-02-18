package com.dipanshu.mycityapp.Weather_dir;


import java.util.ArrayList;

public class data {

    private String visibility, name, cod;

    private ArrayList<Detail> weather;
    private Coord coord;
    private Main main;
    private Wind wind;
    private Clouds clouds;
    private sys sys;

    public data(Wind wind,
                String visibility,
                String name, String cod, Coord coord, ArrayList<Detail> weather, Main main, Clouds clouds, sys sys) {
        this.coord=coord;
        this.visibility = visibility;
        this.sys = sys;
        this.name = name;
        this.cod = cod;
        this.weather = weather;
        this.main = main;
        this.wind=wind;
        this.clouds = clouds;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public ArrayList<Detail> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Detail> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public sys getSys() {
        return sys;
    }

    public void setSys(sys sys) {
        this.sys = sys;
    }
}
