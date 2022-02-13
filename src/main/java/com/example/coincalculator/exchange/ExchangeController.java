package com.example.coincalculator.exchange;

import com.example.coincalculator.config.exception.TimeOutException;
import com.example.coincalculator.config.exception.UnknownCurrencyException;
import com.example.coincalculator.transaction.TransactionDTO;
import com.example.coincalculator.transaction.TransactionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@Log4j2
@RestController
@RequestMapping(path = "/api/v1/exchange")
public class ExchangeController {

    private final ExchangeService exchangeService;
    private final TransactionService transactionService;

    public ExchangeController(ExchangeService exchangeService, TransactionService transactionService) {
        this.exchangeService = exchangeService;
        this.transactionService = transactionService;
    }

    @GetMapping("/getPrice/{from}-{to}")
    public ResponseEntity<PriceDTO> getPrice(@PathVariable String from, @PathVariable String to) throws UnknownCurrencyException {
        BigDecimal price = exchangeService.getPrice(from, to);
        Long cacheId = exchangeService.saveTickerCache(price, from, to);
        return ResponseEntity.ok(PriceDTO.builder().cacheId(cacheId).price(price).build());
    }

    @PostMapping("/save")
    public ResponseEntity<TransactionDTO> save(@Valid @RequestBody ExchangeDTO exchangeDTO) throws TimeOutException {
        return ResponseEntity.ok(transactionService.saveTransaction(exchangeDTO));
    }

}