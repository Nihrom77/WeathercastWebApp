package com.croc.webapp.controllers;

import com.croc.webapp.domain.ApiResponse;
import com.croc.webapp.domain.ChartModel;
import com.croc.webapp.domain.Metric;
import com.croc.webapp.domain.RequestWeatherObject;
import com.croc.webapp.domain.WeatherForecast;
import com.croc.webapp.repository.WeatherForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Контроллер для REST-запросов
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class WeatherRestController {

    private WeatherForecastRepository repository;

    @Autowired
    public WeatherRestController(WeatherForecastRepository repository) {
        this.repository = repository;
    }


    /**
     * Запрос на получение погодныъ сводок.
     * @param requestObject Объект, содержащий параметры запроса
     * @return Объект, содержащий параметры ответа
     */
    @RequestMapping(value = "weather", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<WeatherForecast> getWeatherForecast(@RequestBody RequestWeatherObject requestObject) {
        List<WeatherForecast> weatherForecasts =
                repository.findByDateTimeBetween(requestObject.getDateFrom(), requestObject.getDateTo());
        List<LocalDate> daysRange;

        if (requestObject.getDateTo().isAfter(requestObject.getDateFrom()) || requestObject.getDateTo()
                .isEqual(requestObject.getDateFrom())) {
            long numOfDays = ChronoUnit.DAYS.between(requestObject.getDateFrom(), requestObject.getDateTo());
            daysRange = Stream.iterate(requestObject.getDateFrom(), date -> date.plusDays(1)).limit(numOfDays)
                    .collect(Collectors.toList());
        } else {
            daysRange = new ArrayList<>();
        }

        List<Integer> temperatures = new ArrayList<>();
        weatherForecasts.forEach(t -> temperatures.add(t.getTemperature()));
        ChartModel tempChartModel = new ChartModel(0, daysRange, temperatures, Metric.TEMPERATURE);

        List<Integer> humidities = new ArrayList<>();
        weatherForecasts.forEach(t -> humidities.add(t.getHumidity()));
        ChartModel humidityChartModel = new ChartModel(0, daysRange, humidities, Metric.HUMIDITY);

        List<Integer> pressures = new ArrayList<>();
        weatherForecasts.forEach(t -> pressures.add(t.getPressure()));
        ChartModel pressureChartModel = new ChartModel(0, daysRange, pressures, Metric.PRESSURE);

        List<ChartModel> models = new ArrayList<>();
        models.add(tempChartModel);
        models.add(humidityChartModel);
        models.add(pressureChartModel);

        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.", models);
    }


    /**
     * Запрос на получение списка городов
     * @return Объект, содержащий параметры ответа и список городов
     */
    @RequestMapping(value = "cities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<List<String>> listCities() {
        List<String> cities = new ArrayList<>();
        cities.add("Москва");
        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.", cities);
    }

}
