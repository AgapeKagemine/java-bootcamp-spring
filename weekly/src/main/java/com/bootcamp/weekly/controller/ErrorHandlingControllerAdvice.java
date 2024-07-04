package com.bootcamp.weekly.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// https://reflectoring.io/bean-validation-with-spring-boot/
@AllArgsConstructor
@NoArgsConstructor
@Data
class ValidationErrorResponse { private Set<Violation> violations = new HashSet<>(); }

@AllArgsConstructor
@Data
class Violation {
    private final String fieldName;
    private final String message;
}

@Slf4j
@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {

        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getViolations().add(new Violation("Member", "Field Validation"));
        for (var violation : e.getConstraintViolations()) {
            error.getViolations().add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getViolations().add(new Violation("Method", "Method Validation"));
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getViolations().add(new Violation(fieldError.getField() + 1, fieldError.getDefaultMessage()));
        }
        return error;

    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String onIllegalArgumentException(IllegalArgumentException e) {

        var err = String.format("%s", e.getLocalizedMessage());
        log.error(err);
        return err;

    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public String onIllegalStateException(IllegalStateException e) {

        var err = e.getLocalizedMessage() + " with: " + e.getCause();
        log.error(err);
        return err;
  
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String onHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        var err = String.format("%s", e.getLocalizedMessage());
        log.error(err);
        return err;

    }

}
