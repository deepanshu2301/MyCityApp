package com.dipanshu.mycityapp.Weather_dir;

import java.util.ArrayList;

public class hori_detail {

        private Main main;
        private String dt_txt;
        private ArrayList<Detail> weather;

    public hori_detail(Main main, String dt_txt, ArrayList<Detail> weather) {
        this.main = main;
        this.dt_txt = dt_txt;
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public ArrayList<Detail> getWeather() {
        return weather;
    }

    public void setWeather(ArrayList<Detail> weather) {
        this.weather = weather;
    }
}
