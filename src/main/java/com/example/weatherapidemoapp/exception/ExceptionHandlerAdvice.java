package com.example.weatherapidemoapp.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@ComponentScan
@Component
@Configuration
@RequiredArgsConstructor
public class ExceptionHandlerAdvice {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorMessageDto> handleApiException(ApiException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessageDto(ex.getMessage()));
    }
}
