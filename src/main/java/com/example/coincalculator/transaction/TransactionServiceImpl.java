package com.example.coincalculator.transaction;

import com.example.coincalculator.config.ErrorMessagesConstant;
import com.example.coincalculator.config.exception.TimeOutException;
import com.example.coincalculator.exchange.ExchangeDTO;
import com.example.coincalculator.ticker.Ticker;
import com.example.coincalculator.ticker.TickerRepository;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.sql.Date;
import java.time.Clock;
import java.time.Instant;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TickerRepository tickerRepository;
    private final TransactionMapper transactionMapper;
    private final Clock clock;

    public TransactionServiceImpl(TransactionRepository transactionRepository, TickerRepository tickerRepository,
                                  TransactionMapper transactionMapper, Clock clock) {
        this.transactionRepository = transactionRepository;
        this.tickerRepository = tickerRepository;
        this.transactionMapper = transactionMapper;
        this.clock = clock;
    }

    @Override
    public TransactionDTO saveTransaction(ExchangeDTO exchangeDTO) throws TimeOutException {
        Ticker ticker = tickerRepository.findById(exchangeDTO.getCacheId())
                .orElseThrow(() -> new TimeOutException(ErrorMessagesConstant.TIME_IS_EXCEEDED));

        Transaction transaction = Transaction.builder()
                .coinAmount(exchangeDTO.getAmount().divide(ticker.getPrice(), 4, RoundingMode.HALF_EVEN))
                .fiatAmount(exchangeDTO.getAmount())
                .transactionDate(Date.from(Instant.now(clock)))
                .exchangeType(exchangeDTO.getExchangeType())
                .cryptoCurrencyType(exchangeDTO.getCryptoCurrencyType())
                .fiatCurrencyType(exchangeDTO.getFiatCurrencyType())
                .build();
        Transaction savedTransaction = transactionRepository.save(transaction);

        tickerRepository.deleteById(exchangeDTO.getCacheId());

        return transactionMapper.toFiatCurrencyDTO(savedTransaction);
    }
}
