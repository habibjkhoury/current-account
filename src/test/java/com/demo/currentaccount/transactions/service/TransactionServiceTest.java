package com.demo.currentaccount.transactions.service;

import com.demo.currentaccount.BaseTest;
import com.demo.currentaccount.dto.response.InitiateTransactionResponse;
import com.demo.currentaccount.entity.Account;
import com.demo.currentaccount.entity.Transaction;
import com.demo.currentaccount.enums.TransactionStatus;
import com.demo.currentaccount.repository.TransactionRepository;
import com.demo.currentaccount.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class TransactionServiceTest extends BaseTest {

    @Mock
    TransactionRepository transactionRepository;

    @InjectMocks
    TransactionService transactionService;

    @Test
    public void initiateTransaction_ValidAmount_ShouldReturnApproved(){
        Mockito.when(transactionRepository.save(Mockito.any(Transaction.class))).thenReturn(Transaction.builder().build());
        InitiateTransactionResponse transactionResponse = transactionService.initiate(Mockito.any(Account.class), BigDecimal.TEN);
        Assertions.assertEquals(TransactionStatus.APPROVED, transactionResponse.getStatus());
    }

}
