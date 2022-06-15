package com.demo.currentaccount.service.impl;

import com.demo.currentaccount.dto.request.CreateAccountRequest;
import com.demo.currentaccount.dto.response.CreateAccountResponse;
import com.demo.currentaccount.dto.response.InitiateTransactionResponse;
import com.demo.currentaccount.entity.Account;
import com.demo.currentaccount.entity.Customer;
import com.demo.currentaccount.enums.AccountStatus;
import com.demo.currentaccount.enums.TransactionStatus;
import com.demo.currentaccount.repository.AccountRepository;
import com.demo.currentaccount.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerServiceImpl customerService;

    private final TransactionServiceImpl transactionService;

    @Override
    public CreateAccountResponse create(CreateAccountRequest request) {

        Customer customer = customerService.findCustomerById(request.getCustomerId());


        Account account = Account.builder()
                .customer(customer)
                .balance(BigDecimal.ZERO)
                .build();

        account = accountRepository.save(account);

        InitiateTransactionResponse transactionResponse = InitiateTransactionResponse.builder().status(TransactionStatus.PENDING).build();
        if(request.getInitialCredit() != null && BigDecimal.ZERO.compareTo(request.getInitialCredit()) < 0) {
            transactionResponse = transactionService.initiate(account, request.getInitialCredit());
        }

        if (transactionResponse.getStatus().equals(TransactionStatus.APPROVED)) {
            account = updateAccountBalance(account, transactionResponse.getAmount());
        }

        return CreateAccountResponse.builder()
                .accountId(account.getId())
                .accountStatus(AccountStatus.CREATED)
                .balance(transactionResponse.getAmount())
                .message("Account Created Successfully")
                .build();
    }

    private Account updateAccountBalance(Account account, BigDecimal amount){
        account.setBalance(amount);
        return accountRepository.save(account);
    }

}
