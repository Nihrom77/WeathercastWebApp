package com.croc.webapp.repository;

import com.croc.webapp.domain.WeatherForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * JpaRepository implements CrudRepository
 */
public interface WeatherForecastRepository extends JpaRepository<WeatherForecast, Integer> {
//    List<WeatherForecast> findByDateTime(final LocalDateTime datetime);
    List<WeatherForecast> findByDateTimeBetween (final LocalDate startDatetime, final LocalDate endDatetime);

}
