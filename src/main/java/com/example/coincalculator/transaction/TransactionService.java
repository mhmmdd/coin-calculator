package com.example.coincalculator.transaction;

import com.example.coincalculator.config.exception.TimeOutException;
import com.example.coincalculator.exchange.ExchangeDTO;

public interface TransactionService {
    TransactionDTO saveTransaction(ExchangeDTO exchangeDTO) throws TimeOutException;
}
