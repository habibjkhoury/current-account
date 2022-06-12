package com.demo.currentaccount.common.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerNotFoundResponse {
    private String message;
}
