package com.croc.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherForecastWebApp {

	public static void main(String[] args) {
		SpringApplication.run(WeatherForecastWebApp.class, args);
	}

//	@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
//	@Autowired
//	private WeatherForecastRepository repository;
//
//
//	@PostConstruct
//	public void init() {
//
//		repository.save(new WeatherForecast(LocalDateTime.now(), -10,50,100));
//		repository.save(new WeatherForecast(LocalDateTime.now().plusDays(1), -10,50,100));
//		repository.save(new WeatherForecast(LocalDateTime.now().plusDays(2), -10,50,100));
//		repository.save(new WeatherForecast(LocalDateTime.now().plusDays(3), -10,50,100));
//	}
}
