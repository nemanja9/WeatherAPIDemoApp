package com.example.weatherapidemoapp.service;

import com.example.weatherapidemoapp.client.RestClient;
import com.example.weatherapidemoapp.dto.FootwearEnum;
import com.example.weatherapidemoapp.dto.GearEnum;
import com.example.weatherapidemoapp.dto.MeteoblueResponseDto;
import com.example.weatherapidemoapp.dto.RecommendationDto;
import com.example.weatherapidemoapp.exception.ApiExceptionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final RestClient restClient;

    @Value("${meteoblue.api.key}")
    String apiKey;

    @Value("${meteoblue.base.url}")
    String baseUrl;


    public MeteoblueResponseDto getBasicWeatherForLocation(Double longitude, Double latitude) {
        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl).append("packages/basic-1h_basic-day");
        var requestParams = Map.of("lat", latitude.toString(), "lon", longitude.toString(), "apikey", apiKey);

        return restClient.get(MeteoblueResponseDto.class, sb.toString(), null, requestParams);
    }

    public RecommendationDto getShoeRecommendationForPeriodAndLocation(Double longitude, Double latitude, LocalDateTime dateTime) {
        if (dateTime.isBefore(LocalDateTime.now()) || dateTime.isAfter(LocalDateTime.now().plusDays(7))) {
            throw ApiExceptionFactory.badRequest("Unsupported date! Date has to be in the next 7 days");
        }
        var weatherForecast = getBasicWeatherForLocation(longitude, latitude);
        var desiredTimePosition = getDesiredTimeDateInterval(dateTime, weatherForecast);

        var perceptionAmount = weatherForecast.getData1h().getPrecipitation().get(desiredTimePosition);
        var temperature = weatherForecast.getData1h().getTemperature().get(desiredTimePosition);
        var snow = weatherForecast.getData1h().getSnowfraction().get(desiredTimePosition) == 1;
        var recommendation = new RecommendationDto();

        if (temperature > 25) {
            recommendation.setFootwear(FootwearEnum.SANDALS);
        } else if (temperature > 5) {
            if (perceptionAmount > 0 && !snow) {
                recommendation.setFootwear(FootwearEnum.TALL_BOOTS);
            }
            recommendation.setFootwear(FootwearEnum.LIGHT_BOOTS);
        } else {
            recommendation.setFootwear(FootwearEnum.WINTER_SHOES);
        }

        if (snow && perceptionAmount > 1) {
            recommendation.setGear(GearEnum.HAT_AND_GLOVES);
        } else if (!snow && perceptionAmount > 300) {
            recommendation.setGear(GearEnum.BOAT);
        } else if (!snow && perceptionAmount > 5) {
            recommendation.setGear(GearEnum.UMBRELLA);
        }
        return recommendation;
    }

    public BigDecimal getWindChillFactorForPeriodAndLocation(Double longitude, Double latitude, LocalDateTime dateTime){
        var weatherForecast = getBasicWeatherForLocation(longitude, latitude);
        var desiredTimePosition = getDesiredTimeDateInterval(dateTime, weatherForecast);
        var temperature = weatherForecast.getData1h().getTemperature().get(desiredTimePosition);

        var windSpeed = weatherForecast.getData1h().getWindspeed().get(desiredTimePosition);
        if (windSpeed < 5D) {
            throw ApiExceptionFactory.genericError(HttpStatus.CONFLICT, "Formula not valid for wind speeds under 5km/h");
        }

        BigDecimal result = new BigDecimal(13.12 + 0.6215 * temperature - 11.37 * Math.pow(windSpeed, 0.16) + 0.3965 * temperature * Math.pow(windSpeed, 0.16));
        return result.setScale(2, RoundingMode.HALF_UP);
    }

    private int getDesiredTimeDateInterval(LocalDateTime dateTime, MeteoblueResponseDto weatherForecast) {
        StringBuilder desiredTime = new StringBuilder(dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd ")));

        if (dateTime.getMinute() > 30) {
            desiredTime.append(dateTime.getHour() + 1).append(":00");
        } else {
            desiredTime.append(dateTime.getHour()).append(":00");
        }
        var desiredDate = desiredTime.toString();

        var desiredTimePosition = weatherForecast.getData1h().getTime().indexOf(desiredDate);
        if (desiredTimePosition < 0) {
            throw ApiExceptionFactory.notFound("Weather could not be found for the specified date- time");
        }
        return desiredTimePosition;
    }
}
