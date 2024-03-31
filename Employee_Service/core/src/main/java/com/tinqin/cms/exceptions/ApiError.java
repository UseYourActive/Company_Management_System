package com.tinqin.cms.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiError {
    private HttpStatus status;
    private String message;
    private String details;
}

//    @ExceptionHandler(EmployeeNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ResponseEntity<Object> handleEmployeeNotFoundException(EmployeeNotFoundException e) {
//        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Employee not found", e.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
//    }