package com.demo.currentaccount.service.impl;

import com.demo.currentaccount.dto.response.InitiateTransactionResponse;
import com.demo.currentaccount.entity.Account;
import com.demo.currentaccount.entity.Transaction;
import com.demo.currentaccount.enums.TransactionStatus;
import com.demo.currentaccount.repository.TransactionRepository;
import com.demo.currentaccount.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public InitiateTransactionResponse initiate(Account account, BigDecimal initialCredit){
        Transaction transaction = Transaction.builder().account(account).amount(initialCredit).build();
        transactionRepository.save(transaction);
        return InitiateTransactionResponse.builder().amount(transaction.getAmount()).status(TransactionStatus.APPROVED).build();
    }
}
