package com.croc.webapp.utils;

import com.croc.webapp.domain.WeatherForecast;
import com.croc.webapp.repository.WeatherForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * It implements Spring Boot’s CommandLineRunner so that it gets run after all the beans are created and registered.
 */
@Component
public class DatabaseLoader implements CommandLineRunner {

    private final WeatherForecastRepository repository;

    @Autowired
    public DatabaseLoader(WeatherForecastRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        repository.save(new WeatherForecast(LocalDate.now(), -10,50,1000));
        repository.save(new WeatherForecast(LocalDate.now().plusDays(1), -4,100,900));
        repository.save(new WeatherForecast(LocalDate.now().plusDays(2), 0,50,800));
        repository.save(new WeatherForecast(LocalDate.now().plusDays(3), 7,70,900));
        repository.save(new WeatherForecast(LocalDate.now().plusDays(4), 9,100,900));
        repository.save(new WeatherForecast(LocalDate.now().plusDays(5), 3,90,700));
    }
}
