package com.example.coincalculator.config.exception;

public class TimeOutException extends Exception {

    private static final long serialVersionUID = 1L;

    public TimeOutException(String cause) {
        super(cause);
    }

}