package com.example.coincalculator.transaction;

import com.example.coincalculator.config.ErrorMessagesConstant;
import com.example.coincalculator.config.exception.TimeOutException;
import com.example.coincalculator.exchange.ExchangeDTO;
import com.example.coincalculator.exchange.ExchangeTypeEnum;
import com.example.coincalculator.ticker.Ticker;
import com.example.coincalculator.ticker.TickerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.Clock;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {
    @InjectMocks
    private TransactionServiceImpl transactionService;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private TickerRepository tickerRepository;
    @Spy
    private final TransactionMapper transactionMapper = Mappers.getMapper(TransactionMapper.class);
    @Mock
    private Clock clock;

    @Test
    void saveTransaction() throws TimeOutException {
        // given
        BigDecimal btcPrice = BigDecimal.valueOf(42000);
        Instant date = Instant.now();
        CryptoCurrencyTypeEnum coinType = CryptoCurrencyTypeEnum.BTC;
        FiatCurrencyTypeEnum requestedFiatCurrency = FiatCurrencyTypeEnum.USD;
        BigDecimal fiatAmount = BigDecimal.valueOf(200L);
        ExchangeTypeEnum exchangeTypeEnum = ExchangeTypeEnum.BUY;
        BigDecimal receivedCoinAmount = fiatAmount.divide(btcPrice, 4, RoundingMode.HALF_EVEN);
        Long cacheId = 123L;


        ExchangeDTO exchangeDTO = ExchangeDTO.builder()
                .amount(fiatAmount)
                .cryptoCurrencyType(coinType)
                .fiatCurrencyType(requestedFiatCurrency)
                .exchangeType(exchangeTypeEnum)
                .cacheId(cacheId)
                .build();

        Ticker ticker = Ticker.builder().price(btcPrice).build();
        when(clock.instant()).thenReturn(date);

        Transaction transaction = Transaction.builder()
                .coinAmount(fiatAmount.divide(ticker.getPrice(), 4, RoundingMode.HALF_EVEN))
                .fiatAmount(fiatAmount)
                .transactionDate(Date.from(Instant.now(clock)))
                .exchangeType(exchangeTypeEnum)
                .cryptoCurrencyType(coinType)
                .fiatCurrencyType(requestedFiatCurrency)
                .build();

        when(tickerRepository.findById(exchangeDTO.getCacheId())).thenReturn(Optional.of(ticker));
        when(transactionRepository.save(transaction)).thenReturn(transaction);
        verify(tickerRepository, times(0)).deleteById(exchangeDTO.getCacheId());


        // when
        TransactionDTO transactionDTO = transactionService.saveTransaction(exchangeDTO);

        // then
        TransactionDTO receivedTransactionDTO = transactionMapper.toFiatCurrencyDTO(transaction);
        assertThat(transactionDTO).isEqualTo(receivedTransactionDTO);
        assertEquals(receivedCoinAmount, transactionDTO.getReceivedCoinAmount());
        assertEquals(requestedFiatCurrency, transactionDTO.getRequestedFiatCurrency());
        verify(tickerRepository, times(1)).deleteById(exchangeDTO.getCacheId());
    }

    @Test
    void saveTransactionWithException() {
        // given
        ExchangeDTO exchangeDTO = ExchangeDTO.builder().build();

        // when, then
        TimeOutException exception = assertThrows(TimeOutException.class, () -> transactionService.saveTransaction(exchangeDTO));
        assertEquals(exception.getMessage(), ErrorMessagesConstant.TIME_IS_EXCEEDED);
    }
}