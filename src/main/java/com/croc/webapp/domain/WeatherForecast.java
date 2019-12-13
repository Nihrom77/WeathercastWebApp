package com.croc.webapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class WeatherForecast {

    @Id
    @GeneratedValue
    private int id;

    private LocalDate dateTime;
    private int temperature;
    private int humidity;
    private int pressure;

    public WeatherForecast() {

    }

    public WeatherForecast(LocalDate dateTime,
                           int temperature,
                           int humidity,
                           int pressure) {
        this.dateTime = dateTime;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherForecast forecast = (WeatherForecast) o;
        return Objects.equals(id, forecast.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, temperature, humidity, pressure);
    }

    @Override
    public String toString() {
        return "WeatherForecast " + getId() + " dateTime=" + getDateTime() +
                " humidity=" + getHumidity() + " pressure=" + getPressure() + " temprature=" + getTemperature();
    }
}
