package com.example.coincalculator.exchange;

import com.example.coincalculator.config.exception.UnknownCurrencyException;

import java.math.BigDecimal;

public interface ExchangeService {
    BigDecimal getPrice(String from, String to) throws UnknownCurrencyException;

    Long saveTickerCache(BigDecimal price, String from, String to);
}
