package com.demo.currentaccount.dto.response;

import com.demo.currentaccount.entity.Account;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomerInquiryResponse {

    private Long id;
    private String name;
    private String surname;
    private List<Account> accounts;
}
