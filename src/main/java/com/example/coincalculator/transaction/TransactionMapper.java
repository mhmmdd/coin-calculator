package com.example.coincalculator.transaction;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mappings({
            @Mapping(source = "coinAmount", target = "receivedCoinAmount"),
            @Mapping(source = "transactionDate", target = "date"),
            @Mapping(source = "cryptoCurrencyType", target = "coinType"),
            @Mapping(source = "fiatCurrencyType", target = "requestedFiatCurrency"),
    })
    TransactionDTO toFiatCurrencyDTO(Transaction transaction);

    Transaction toEntity(TransactionDTO dto);
}
