package com.example.coincalculator.transaction;

public enum CryptoCurrencyTypeEnum {
    BTC("BTC"),
    ETH("ETH");

    private final String value;

    CryptoCurrencyTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static CryptoCurrencyTypeEnum fromValue(String text) {
        for (CryptoCurrencyTypeEnum currencyEnum : CryptoCurrencyTypeEnum.values()) {
            if (String.valueOf(currencyEnum.value).equals(text)) {
                return currencyEnum;
            }
        }
        return null;
    }
}
