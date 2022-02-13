package com.example.coincalculator.transaction;

import com.example.coincalculator.config.entity.AbstractAuditEntity;
import com.example.coincalculator.config.entity.EnumUppercaseConverter;
import com.example.coincalculator.exchange.ExchangeTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction extends AbstractAuditEntity<Long> {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal coinAmount;

    @Column
    private BigDecimal fiatAmount;

    @Column
    private Date transactionDate;

    @Column
    @Enumerated(EnumType.STRING)
    @Convert(converter = EnumUppercaseConverter.class)
    private ExchangeTypeEnum exchangeType;

    @Column
    @Enumerated(EnumType.STRING)
    @Convert(converter = EnumUppercaseConverter.class)
    private CryptoCurrencyTypeEnum cryptoCurrencyType;

    @Column
    @Enumerated(EnumType.STRING)
    @Convert(converter = EnumUppercaseConverter.class)
    private FiatCurrencyTypeEnum fiatCurrencyType;

}