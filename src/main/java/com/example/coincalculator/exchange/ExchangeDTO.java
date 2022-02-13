package com.example.coincalculator.exchange;

import com.example.coincalculator.config.ErrorMessagesConstant;
import com.example.coincalculator.transaction.CryptoCurrencyTypeEnum;
import com.example.coincalculator.transaction.FiatCurrencyTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ExchangeDTO {

    @NotNull
    @Min(value = 25, message = ErrorMessagesConstant.INVALID_MIN_FIAT_AMOUNT)
    @Max(value = 5000, message = ErrorMessagesConstant.INVALID_MAX_FIAT_AMOUNT)
    private BigDecimal amount;

    @NotNull
    private FiatCurrencyTypeEnum fiatCurrencyType;
    @NotNull
    private CryptoCurrencyTypeEnum cryptoCurrencyType;
    @NotNull
    private ExchangeTypeEnum exchangeType;
    @NotNull
    private Long cacheId;
}

