package com.example.weatherapidemoapp.repository;

import com.example.weatherapidemoapp.entity.WeatherEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherEntryRepository extends JpaRepository<WeatherEntryEntity, Long> {
    List<WeatherEntryEntity> findAllByDateTimeAndLongitudeAndLatitudeOrderByLastUpdatedDesc(@Param("date_time") LocalDateTime dateTime,
                                                                                            @Param("longitude") double longitude,
                                                                                            @Param("latitude") double latitude);
}
