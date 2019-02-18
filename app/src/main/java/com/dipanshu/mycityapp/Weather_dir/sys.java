package com.dipanshu.mycityapp.Weather_dir;

class sys {
    private String sunrise,sunset,country;

    sys(String sunrise, String sunset , String country){
        this.sunrise=sunrise;
        this.sunset=sunset;
        this.country=country;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
