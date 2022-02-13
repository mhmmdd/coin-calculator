package com.example.coincalculator.transaction;

public enum FiatCurrencyTypeEnum {
    USD("USD"),
    EUR("EUR");

    private final String value;

    FiatCurrencyTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static FiatCurrencyTypeEnum fromValue(String text) {
        for (FiatCurrencyTypeEnum currencyEnum : FiatCurrencyTypeEnum.values()) {
            if (String.valueOf(currencyEnum.value).equals(text)) {
                return currencyEnum;
            }
        }
        return null;
    }
}
