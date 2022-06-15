package com.demo.currentaccount.service;

import com.demo.currentaccount.dto.response.InitiateTransactionResponse;
import com.demo.currentaccount.entity.Account;

import java.math.BigDecimal;

public interface TransactionService {
    InitiateTransactionResponse initiate(Account account, BigDecimal initialCredit);
}
