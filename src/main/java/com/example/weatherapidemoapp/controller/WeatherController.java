package com.example.weatherapidemoapp.controller;

import com.example.weatherapidemoapp.dto.MeteoblueResponseDto;
import com.example.weatherapidemoapp.dto.RecommendationDto;
import com.example.weatherapidemoapp.service.WeatherService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController("Weather controller")
@RequestMapping("/api/weather")
@Tag(name = "Weather controller", description = "Public weather operations")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/general/{longitude}/{latitude}")
    public ResponseEntity<MeteoblueResponseDto> getBasicWeatherForLocation(@PathVariable Double longitude,
                                                                           @PathVariable Double latitude) {
        return ResponseEntity.ok(weatherService.getBasicWeatherForLocation(longitude, latitude));
    }

    @GetMapping("/recommendation/{longitude}/{latitude}")
    public ResponseEntity<RecommendationDto> getRecommendationForPeriodAndLocation(@PathVariable Double longitude,
                                                                                   @PathVariable Double latitude,
                                                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        return ResponseEntity.ok(weatherService.getShoeRecommendationForPeriodAndLocation(longitude, latitude, dateTime));
    }

    @GetMapping("/wind-chill-factor/{longitude}/{latitude}")
    public ResponseEntity<BigDecimal> getWindChillFactorForPeriodAndLocation(@PathVariable Double longitude,
                                                                             @PathVariable Double latitude,
                                                                             @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        return ResponseEntity.ok(weatherService.getWindChillFactorForPeriodAndLocation(longitude, latitude, dateTime));
    }


}
