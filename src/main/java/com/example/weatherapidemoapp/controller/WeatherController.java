package com.example.weatherapidemoapp.controller;

import com.example.weatherapidemoapp.dto.MeteoblueResponseDto;
import com.example.weatherapidemoapp.dto.RecommendationDto;
import com.example.weatherapidemoapp.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController("Weather controller")
@RequestMapping("/api/weather")
@Tag(name = "Weather controller", description = "Public weather operations")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @Operation(description = "Retrieves data from meteoBlue API, saves it into the db and displays it")
    @GetMapping("/general/{longitude}/{latitude}")
    public ResponseEntity<MeteoblueResponseDto> getBasicWeatherForLocation(@PathVariable Double longitude,
                                                                           @PathVariable Double latitude) {
        return ResponseEntity.ok(weatherService.getBasicWeatherForLocation(longitude, latitude));
    }

    @Operation(description = "Gives recommendation for footwear and gear needed for the weather conditions in a specified location for the specified time")
    @GetMapping("/recommendation/{longitude}/{latitude}")
    public ResponseEntity<RecommendationDto> getRecommendationForPeriodAndLocation(@PathVariable Double longitude,
                                                                                   @PathVariable Double latitude,
                                                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        return ResponseEntity.ok(weatherService.getRecommendationForPeriodAndLocation(longitude, latitude, dateTime));
    }

    @Operation(description = "Calculates wind chill pattern in a specified location for the specified time")
    @GetMapping("/wind-chill-factor/{longitude}/{latitude}")
    public ResponseEntity<BigDecimal> getWindChillFactorForPeriodAndLocation(@PathVariable Double longitude,
                                                                             @PathVariable Double latitude,
                                                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        return ResponseEntity.ok(weatherService.getWindChillFactorForPeriodAndLocation(longitude, latitude, dateTime));
    }


}
