package com.example.weatherapidemoapp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "weather_entry")
public class WeatherEntryEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Column(name = "precipitation")
    private Double precipitation;

    @Column(name = "snow_fraction")
    private boolean snowFraction;

    @Column(name = "is_daylight")
    private boolean isDaylight;

    @Column(name = "rain_spot")
    private String rainSpot;

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "felt_temperature")
    private double feltTemperature;

    @Column(name = "wind_speed")
    private double windSpeed;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "sea_level_pressure")
    private double seaLevelPressure;

    @Column(name = "pictocode")
    private int pictocode;

    @Column(name = "wind_direction")
    private int windDirection;

    @Column(name = "relative_humidity")
    private int relativeHumidity;

    @Column(name = "precipitation_probability")
    private int precipitationProbability;

    @Column(name = "convective_precipitation")
    private double convectivePrecipitation;

    @Column(name = "uv_index")
    private int uvIndex;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @Column(name = "time_unit")
    private String timeUnit;

    @Column(name = "precipitation_probability_unit")
    private String precipitationProbabilityUnit;

    @Column(name = "pressure_unit")
    private String pressureUnit;

    @Column(name = "relative_humidity_unit")
    private String relativeHumidityUnit;

    @Column(name = "co_unit")
    private String coUnit;

    @Column(name = "precipitation_unit")
    private String precipitationUnit;

    @Column(name = "temperature_unit")
    private String temperatureUnit;

    @Column(name = "wind_speed_unit")
    private String windSpeedUnit;

    @Column(name = "wind_direction_unit")
    private String windDirectionUnit;

    @Column(name = "predictability_unit")
    private String predictabilityUnit;
}
