package com.tinqin.cms.handlers;

import com.tinqin.cms.exceptions.NotMatchingPasswordsException;
import com.tinqin.cms.exceptions.UserNotFoundException;
import com.tinqin.cms.exceptions.UsernameAlreadyExistsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<String> handleRestClientException(RestClientException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error occurred during API call! " + e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found! " + e.getMessage());
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<String> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User already exists in the database! " + e.getMessage());
    }

    @ExceptionHandler(NotMatchingPasswordsException.class)
    public ResponseEntity<String> handleNotMatchingPasswordsException(NotMatchingPasswordsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords do not match! " + e.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> listOfErrors = ex.getBindingResult().getAllErrors();

        listOfErrors.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(DataIntegrityViolationException.class)
//    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
//        if (e.getCause() instanceof PSQLException) {
//            PSQLException pgsqlException = (PSQLException) e.getCause();
//            String sqlState = pgsqlException.getSQLState();
//            log.error("SQL State: {}", sqlState);
//            log.error("Exception details:", e);
//
//            if (sqlState.equals("23505")) { // PostgreSQL error code for unique constraint violation
//                return ResponseEntity.status(HttpStatus.CONFLICT)
//                        .body("Username already exists. Please choose a different username.");
//            }
//        }
//        return ResponseEntity.status(HttpStatus.CONFLICT)
//                .body("An error occurred: " + e.getMessage());
//    }
}
