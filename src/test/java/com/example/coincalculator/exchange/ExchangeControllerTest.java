package com.example.coincalculator.exchange;

import com.example.coincalculator.transaction.CryptoCurrencyTypeEnum;
import com.example.coincalculator.transaction.FiatCurrencyTypeEnum;
import com.example.coincalculator.transaction.TransactionDTO;
import com.example.coincalculator.transaction.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExchangeController.class)
class ExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ExchangeService exchangeService;

    @MockBean
    private TransactionService transactionService;

    @Test
    void getPrice() throws Exception {
        // given
        String from = "USD";
        String to = "BTC";
        BigDecimal price = BigDecimal.valueOf(42000);

        when(exchangeService.getPrice(from, to)).thenReturn(price);

        // when, then
        PriceDTO priceDTO = PriceDTO.builder().price(price).cacheId(0L).build();
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(get("/api/v1/exchange/getPrice/" + from + "-" + to))
                .andExpect(content().json(mapper.writeValueAsString(priceDTO)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void save() throws Exception {
        // given
        CryptoCurrencyTypeEnum cryptoCurrencyTypeEnum = CryptoCurrencyTypeEnum.BTC;
        FiatCurrencyTypeEnum fiatCurrencyTypeEnum = FiatCurrencyTypeEnum.USD;
        ExchangeTypeEnum exchangeTypeEnum = ExchangeTypeEnum.BUY;
        BigDecimal amount = BigDecimal.valueOf(100);
        Long cacheId = 11L;

        ExchangeDTO exchangeDTO = ExchangeDTO.builder()
                .amount(amount)
                .fiatCurrencyType(fiatCurrencyTypeEnum)
                .cryptoCurrencyType(cryptoCurrencyTypeEnum)
                .exchangeType(exchangeTypeEnum)
                .cacheId(cacheId)
                .build();

        TransactionDTO transactionDTO = TransactionDTO.builder().build();

        when(transactionService.saveTransaction(exchangeDTO)).thenReturn(transactionDTO);

        // when, then
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(post("/api/v1/exchange/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(exchangeDTO)))
                .andExpect(content().json(mapper.writeValueAsString(transactionDTO)))
                .andExpect(status().is2xxSuccessful());
    }
}