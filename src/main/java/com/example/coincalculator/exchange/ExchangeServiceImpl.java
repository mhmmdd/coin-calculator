package com.example.coincalculator.exchange;

import com.example.coincalculator.config.ErrorMessagesConstant;
import com.example.coincalculator.config.exception.UnknownCurrencyException;
import com.example.coincalculator.ticker.ExchangeRatesApiFeignClient;
import com.example.coincalculator.ticker.Ticker;
import com.example.coincalculator.ticker.TickerDTO;
import com.example.coincalculator.ticker.TickerRepository;
import com.example.coincalculator.transaction.CryptoCurrencyTypeEnum;
import com.example.coincalculator.transaction.FiatCurrencyTypeEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    private final ExchangeRatesApiFeignClient exchangeRatesApiFeignClient;
    private final TickerRepository tickerRepository;

    public ExchangeServiceImpl(ExchangeRatesApiFeignClient exchangeRatesApiFeignClient,
                               TickerRepository tickerRepository) {
        this.exchangeRatesApiFeignClient = exchangeRatesApiFeignClient;
        this.tickerRepository = tickerRepository;
    }

    @Override
    public BigDecimal getPrice(String from, String to) throws UnknownCurrencyException {
        if(CryptoCurrencyTypeEnum.fromValue(from) == null || FiatCurrencyTypeEnum.fromValue(to) == null) {
            throw new UnknownCurrencyException(ErrorMessagesConstant.UNKNOWN_CURRENCY);
        }

        String pair = from + "-" + to;
        TickerDTO tickerDTO = exchangeRatesApiFeignClient.getTicker(pair);
        return tickerDTO.getLast_trade_price();
    }

    @Override
    public Long saveTickerCache(BigDecimal price, String from, String to) {
        Ticker ticker = Ticker.builder()
                .from(from)
                .to(to)
                .price(price)
                .build();
        return tickerRepository.save(ticker).getId();
    }
}
