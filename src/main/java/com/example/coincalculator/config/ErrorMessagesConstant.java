package com.example.coincalculator.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorMessagesConstant {

    public static final String TIME_IS_EXCEEDED = "Price time is exceeded!";
    public static final String UNKNOWN_CURRENCY = "Unknown currency!";
    public static final String INVALID_MIN_FIAT_AMOUNT = "ERROR-1001: Min price value";
    public static final String INVALID_MAX_FIAT_AMOUNT = "ERROR-1002: Max price value";

}