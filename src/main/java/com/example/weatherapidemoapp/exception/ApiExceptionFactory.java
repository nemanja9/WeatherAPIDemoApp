package com.example.weatherapidemoapp.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiExceptionFactory {

    public static ApiException notFound(String message) {
        return new ApiException(HttpStatus.NOT_FOUND, ErrorCode.NOT_FOUND, message);
    }

    public static ApiException badRequest(String message) {
        return new ApiException(HttpStatus.BAD_REQUEST, ErrorCode.BAD_REQUEST, message);
    }

    public static ApiException genericError(HttpStatus httpStatus, String message) {
        return new ApiException(httpStatus, ErrorCode.GENERIC_CLIENT_ERROR, message);
    }
}
