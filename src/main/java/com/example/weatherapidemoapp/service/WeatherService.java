package com.example.weatherapidemoapp.service;

import com.example.weatherapidemoapp.client.RestClient;
import com.example.weatherapidemoapp.dto.FootwearEnum;
import com.example.weatherapidemoapp.dto.GearEnum;
import com.example.weatherapidemoapp.dto.MeteoblueResponseDto;
import com.example.weatherapidemoapp.dto.RecommendationDto;
import com.example.weatherapidemoapp.entity.WeatherEntryEntity;
import com.example.weatherapidemoapp.exception.ApiExceptionFactory;
import com.example.weatherapidemoapp.repository.WeatherEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final RestClient restClient;
    private final WeatherEntryRepository weatherEntryRepository;

    @Value("${meteoblue.api.key}")
    String apiKey;

    @Value("${meteoblue.base.url}")
    String baseUrl;


    public MeteoblueResponseDto getBasicWeatherForLocation(Double longitude, Double latitude) {
        StringBuilder sb = new StringBuilder();
        sb.append(baseUrl).append("packages/basic-1h_basic-day");
        var requestParams = Map.of("lat", latitude.toString(), "lon", longitude.toString(), "apikey", apiKey);

        var forecast = restClient.get(MeteoblueResponseDto.class, sb.toString(), null, requestParams);
        saveToDb(forecast);
        return forecast;
    }

    public RecommendationDto getRecommendationForPeriodAndLocation(Double longitude, Double latitude, LocalDateTime dateTime) {
        if (dateTime.isBefore(LocalDateTime.now()) || dateTime.isAfter(LocalDateTime.now().plusDays(7))) {
            throw ApiExceptionFactory.badRequest("Unsupported date! Date has to be in the next 7 days");
        }
        var latestData = getWeatherDataForPeriodAndLocation(longitude, latitude, dateTime);

        var perceptionAmount = latestData.getPrecipitation();
        var temperature = latestData.getTemperature();
        var snow = latestData.isSnowFraction();
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

    public BigDecimal getWindChillFactorForPeriodAndLocation(Double longitude, Double latitude, LocalDateTime dateTime) {
        var latestData = getWeatherDataForPeriodAndLocation(longitude, latitude, dateTime);
        var temperature = latestData.getTemperature();
        var windSpeed = latestData.getWindSpeed();

        if (windSpeed < 5D) {
            throw ApiExceptionFactory.genericError(HttpStatus.CONFLICT, "Formula not valid for wind speeds under 5km/h");
        }

        BigDecimal result = new BigDecimal(13.12 + 0.6215 * temperature - 11.37 * Math.pow(windSpeed, 0.16) + 0.3965 * temperature * Math.pow(windSpeed, 0.16));
        return result.setScale(2, RoundingMode.HALF_UP);
    }

    private WeatherEntryEntity getWeatherDataForPeriodAndLocation(Double longitude, Double latitude, LocalDateTime dateTime) {
        var forecastFromApi = getBasicWeatherForLocation(longitude, latitude);
        var dateWithoutMinutes = dateTime.minusMinutes(dateTime.getMinute()).minusSeconds(dateTime.getSecond());
        var forecastFromDb = weatherEntryRepository.findAllByDateTimeAndLongitudeAndLatitudeOrderByLastUpdatedDesc(dateWithoutMinutes, forecastFromApi.getMetadata().getLongitude(), forecastFromApi.getMetadata().getLatitude());
        if (forecastFromDb == null || forecastFromDb.isEmpty()) {
            throw ApiExceptionFactory.notFound("Forecast could not be found");
        }
        return forecastFromDb.get(0);
    }

    private void saveToDb(MeteoblueResponseDto forecast) {
        var toSave = new LinkedList<WeatherEntryEntity>();

        for (int i = 0; i < forecast.getData1h().getTime().size(); i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            toSave.add(WeatherEntryEntity.builder()
                    .dateTime(LocalDateTime.parse(forecast.getData1h().getTime().get(i), formatter))
                    .precipitation(forecast.getData1h().getPrecipitation().get(i))
                    .snowFraction(forecast.getData1h().getSnowfraction().get(i).equals(1))
                    .rainSpot(forecast.getData1h().getRainspot().get(i))
                    .temperature(forecast.getData1h().getTemperature().get(i))
                    .feltTemperature(forecast.getData1h().getFelttemperature().get(i))
                    .pictocode(forecast.getData1h().getPictocode().get(i))
                    .windSpeed(forecast.getData1h().getWindspeed().get(i))
                    .windDirection(forecast.getData1h().getWinddirection().get(i))
                    .relativeHumidity(forecast.getData1h().getRelativehumidity().get(i))
                    .seaLevelPressure(forecast.getData1h().getSealevelpressure().get(i))
                    .precipitationProbability(forecast.getData1h().getPrecipitationProbability().get(i))
                    .convectivePrecipitation(forecast.getData1h().getConvectivePrecipitation().get(i))
                    .isDaylight(forecast.getData1h().getIsdaylight().get(i) == 1)
                    .uvIndex(forecast.getData1h().getUvindex().get(i))
                    .lastUpdated(LocalDateTime.now())
                    .latitude(forecast.getMetadata().getLatitude())
                    .longitude(forecast.getMetadata().getLongitude())
                    .precipitationProbabilityUnit(forecast.getUnits().getPrecipitationProbability())
                    .pressureUnit(forecast.getUnits().getPressure())
                    .relativeHumidityUnit(forecast.getUnits().getRelativehumidity())
                    .coUnit(forecast.getUnits().getCo())
                    .precipitationUnit(forecast.getUnits().getPrecipitation())
                    .temperatureUnit(forecast.getUnits().getTemperature())
                    .windSpeedUnit(forecast.getUnits().getWindspeed())
                    .windDirectionUnit(forecast.getUnits().getWinddirection())
                    .predictabilityUnit(forecast.getUnits().getPredictability())
                    .timeUnit(forecast.getUnits().getTime())
                    .build());
        }
        weatherEntryRepository.saveAll(toSave);
    }

}
