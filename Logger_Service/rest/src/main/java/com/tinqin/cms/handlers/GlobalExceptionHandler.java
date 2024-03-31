package com.tinqin.cms.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RestClientException.class)
    @ResponseBody
    public ResponseEntity<String> handleRestClientException(RestClientException e) {
        log.error("Error occurred during API call", e);
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error occurred during API call! " + e.getMessage());
    }
}
