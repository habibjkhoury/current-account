package com.demo.currentaccount.dto.response;

import com.demo.currentaccount.enums.TransactionStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class InitiateTransactionResponse {
    BigDecimal amount;
    TransactionStatus status;
}
