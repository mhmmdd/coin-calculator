package com.example.coincalculator.config.exception;

public class UnknownCurrencyException extends Exception {

    private static final long serialVersionUID = 1L;

    public UnknownCurrencyException(String cause) {
        super(cause);
    }

}