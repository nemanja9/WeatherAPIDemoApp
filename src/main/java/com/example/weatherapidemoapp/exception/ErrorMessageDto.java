package com.example.weatherapidemoapp.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessageDto {
    private String message;
}
