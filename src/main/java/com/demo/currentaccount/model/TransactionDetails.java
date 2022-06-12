package com.demo.currentaccount.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TransactionDetails {

    private Long id;
    private BigDecimal amount;
    private Long accountId;
    private BigDecimal accountBalance;
}
