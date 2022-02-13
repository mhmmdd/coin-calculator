package com.example.coincalculator.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TransactionDTO implements Serializable {
    private BigDecimal receivedCoinAmount;
    private Date date;
    private CryptoCurrencyTypeEnum coinType;
    private FiatCurrencyTypeEnum requestedFiatCurrency;
    private BigDecimal fiatAmount;
}
