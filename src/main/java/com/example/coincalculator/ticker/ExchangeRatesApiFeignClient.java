package com.example.coincalculator.ticker;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "exchange-rates-api",
        url = "${external-api.exchange-rates-api.url}")
public interface ExchangeRatesApiFeignClient {

    @GetMapping(value = "/tickers/{pair}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    TickerDTO getTicker(@PathVariable(value = "pair") String pair);
}