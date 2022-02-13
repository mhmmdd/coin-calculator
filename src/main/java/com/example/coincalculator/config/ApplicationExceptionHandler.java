package com.example.coincalculator.config;

import com.example.coincalculator.config.exception.TimeOutException;
import com.example.coincalculator.config.exception.UnknownCurrencyException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(value = TimeOutException.class)
    public ResponseEntity<Object> handleTimeOutException(TimeOutException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = UnknownCurrencyException.class)
    public ResponseEntity<Object> handleUnknownCurrencyException(UnknownCurrencyException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> processValidationError(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return ResponseEntity.badRequest().body(processFieldErrors(fieldErrors));
    }

    private List<FieldErrorDTO> processFieldErrors(List<FieldError> fieldErrors) {
        List<FieldErrorDTO> fieldErrorDTOs = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            fieldErrorDTOs.add(FieldErrorDTO.builder().field(fieldError.getField()).message(fieldError.getDefaultMessage()).build());
        }

        return fieldErrorDTOs;
    }

}
