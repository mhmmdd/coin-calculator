package com.example.coincalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@EnableFeignClients
@SpringBootApplication
public class CoinCalculatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoinCalculatorApplication.class, args);
    }

    @Bean
    public Clock getClock() {
        return Clock.systemDefaultZone();
    }

}
