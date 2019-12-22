package com.croc.webapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * Класс, содержащий параметры запроса для обновления данных на графиках
 */
public class RequestWeatherObject
{
    private int id;

    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate dateFrom;

    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy") //2019-12-08T22:16:15.940Z
    private LocalDate dateTo;

    private String city;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString(){
        return "Id = "+id+ " dateFrom="+dateFrom+" dateTo="+dateTo+
                " city="+city;
    }
}
