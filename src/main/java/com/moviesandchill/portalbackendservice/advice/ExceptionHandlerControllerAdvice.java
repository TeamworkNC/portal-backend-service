package com.moviesandchill.portalbackendservice.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moviesandchill.portalbackendservice.exception.ApiError;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientResponseException;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerControllerAdvice {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @ExceptionHandler(RestClientResponseException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(final RestClientResponseException exception) {
        String body = exception.getResponseBodyAsString();
        int status = exception.getRawStatusCode();

        try {
            var apiError = objectMapper.readValue(body, ApiError.class);
            return new ResponseEntity<>(apiError, HttpStatus.valueOf(status));
        } catch (Exception e) {
            exception.printStackTrace();
            String errorMessage = "received message from microservice with unknown response format: " + body;
            var apiError = new ApiError(errorMessage);
            return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ApiError handleException(final Exception exception) {
        exception.printStackTrace();
        return new ApiError(exception);
    }

}
