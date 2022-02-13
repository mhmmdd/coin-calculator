package com.example.coincalculator.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldErrorDTO {
 
    private String field;
 
    private String message;
 
    public FieldErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }
}