package com.example.myweather.Model;

import android.widget.TextView;

import java.util.List;

public class City {
    private String country;
    private String name;
    private Double temp;
    private Double humidity;
    private Double pressure;
    private Double clouds;
    private Wind wind;
    private List<Weather> weather;

    public City() {}

    public City(String country, String name, Double temp, Double humidity, Double pressure, Double clouds, Wind wind, List<Weather> weather) {
        this.country = country;
        this.name = name;
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.clouds = clouds;
        this.wind = wind;
        this.weather = weather;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getClouds() {
        return clouds;
    }

    public void setClouds(Double clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
