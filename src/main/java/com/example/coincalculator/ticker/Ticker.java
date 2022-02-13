package com.example.coincalculator.ticker;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@RedisHash(value = "ticker", timeToLive = 100)
public class Ticker implements Serializable {
    @Id
    private Long id;
    private String from;
    private String to;
    private BigDecimal price;
}