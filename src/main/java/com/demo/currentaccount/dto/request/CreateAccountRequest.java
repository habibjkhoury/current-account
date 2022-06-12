package com.demo.currentaccount.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CreateAccountRequest {
    @NotNull
    private Long customerId;

    private BigDecimal initialCredit = BigDecimal.ZERO;
}
