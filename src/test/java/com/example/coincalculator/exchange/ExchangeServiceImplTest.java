package com.example.coincalculator.exchange;

import com.example.coincalculator.config.ErrorMessagesConstant;
import com.example.coincalculator.config.exception.UnknownCurrencyException;
import com.example.coincalculator.ticker.ExchangeRatesApiFeignClient;
import com.example.coincalculator.ticker.Ticker;
import com.example.coincalculator.ticker.TickerDTO;
import com.example.coincalculator.ticker.TickerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExchangeServiceImplTest {

    @InjectMocks
    private ExchangeServiceImpl exchangeService;
    @Mock
    private TickerRepository tickerRepository;
    @Mock
    private ExchangeRatesApiFeignClient exchangeRatesApiFeignClient;

    @Test
    void getPrice() throws UnknownCurrencyException {
        // given
        String from = "BTC";
        String to = "USD";
        BigDecimal price = BigDecimal.valueOf(42000);
        TickerDTO tickerDTO = TickerDTO.builder().last_trade_price(price).build();
        when(exchangeRatesApiFeignClient.getTicker(from + "-" + to)).thenReturn(tickerDTO);

        // when
        BigDecimal receivedPrice = exchangeService.getPrice(from, to);

        // then
        assertThat(receivedPrice).isEqualTo(price);
    }

    @Test
    void getPriceWithException() {
        // given
        String from = "BTCC";
        String to = "USD";

        // when, then
        UnknownCurrencyException exception = assertThrows(UnknownCurrencyException.class, () -> exchangeService.getPrice(from, to));
        assertEquals(exception.getMessage(), ErrorMessagesConstant.UNKNOWN_CURRENCY);
    }

    @Test
    void saveTickerCache() {
        // given
        String from = "BTC";
        String to = "USD";
        BigDecimal price = BigDecimal.valueOf(42000);

        Ticker ticker = Ticker.builder()
                .from(from)
                .to(to)
                .price(price)
                .build();
        Ticker savedTicker = Ticker.builder().id(123L).build();
        when(tickerRepository.save(ticker)).thenReturn(savedTicker);

        // when
        Long cacheId = exchangeService.saveTickerCache(price, from, to);

        // then
        assertThat(cacheId).isEqualTo(savedTicker.getId());
    }
}