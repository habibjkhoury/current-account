package com.demo.currentaccount.dto.response;

import com.demo.currentaccount.enums.AccountStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CreateAccountResponse {
    private Long accountId;
    private BigDecimal balance;
    private AccountStatus accountStatus;
    private String message;
}
