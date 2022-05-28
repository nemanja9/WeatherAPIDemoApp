package com.example.weatherapidemoapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "WeatherAPIDemoApp",
                version = "1.0.0"))
@SpringBootApplication
public class WeatherApiDemoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApiDemoAppApplication.class, args);
    }

}
