package com.demo.currentaccount.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
public class CreateAccountRequest {
    @NotNull
    private Long customerId;

    @Builder.Default private BigDecimal initialCredit = BigDecimal.ZERO;
}
