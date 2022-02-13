package com.example.coincalculator.exchange;

public enum ExchangeTypeEnum {
    BUY("BUY"),
    SELL("SELL");

    private final String value;

    ExchangeTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static ExchangeTypeEnum fromValue(String text) {
        for (ExchangeTypeEnum typeEnum : ExchangeTypeEnum.values()) {
            if (String.valueOf(typeEnum.value).equals(text)) {
                return typeEnum;
            }
        }
        return null;
    }
}
