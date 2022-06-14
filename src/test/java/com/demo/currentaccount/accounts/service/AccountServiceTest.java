package com.demo.currentaccount.accounts.service;

import com.demo.currentaccount.BaseTest;
import com.demo.currentaccount.dto.request.CreateAccountRequest;
import com.demo.currentaccount.dto.response.CreateAccountResponse;
import com.demo.currentaccount.dto.response.InitiateTransactionResponse;
import com.demo.currentaccount.entity.Account;
import com.demo.currentaccount.entity.Customer;
import com.demo.currentaccount.enums.AccountStatus;
import com.demo.currentaccount.enums.TransactionStatus;
import com.demo.currentaccount.repository.AccountRepository;
import com.demo.currentaccount.service.AccountService;
import com.demo.currentaccount.service.CustomerService;
import com.demo.currentaccount.service.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class AccountServiceTest extends BaseTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    CustomerService customerService;

    @Mock
    TransactionService transactionService;

    @InjectMocks
    AccountService accountService;

    static Account account;

    static Customer customer;

    static InitiateTransactionResponse transactionResponse;

    @BeforeAll
    static void setup() {
        customer = Customer.builder().id(1L).build();
        account = Account.builder().id(1L).balance(BigDecimal.ZERO).customer(customer).build();
        transactionResponse = InitiateTransactionResponse.builder().amount(BigDecimal.ZERO).status(TransactionStatus.APPROVED).build();
    }


    @Test
    public void createNewAccount_ExistingCustomerZeroBalance_ShouldReturnCreated() {

        Mockito.when(customerService.findCustomerById(1L)).thenReturn(customer);
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);

        CreateAccountRequest request = CreateAccountRequest.builder().customerId(1L).build();

        CreateAccountResponse response = accountService.create(request);

        Assertions.assertEquals(AccountStatus.CREATED, response.getAccountStatus());
    }

    @Test
    public void createNewAccount_ExistingCustomerWithBalance_ShouldReturnCreated() {

        Mockito.when(customerService.findCustomerById(1L)).thenReturn(customer);
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);
        Mockito.when(transactionService.initiate(Mockito.any(Account.class), Mockito.any(BigDecimal.class))).thenReturn(transactionResponse);

        CreateAccountRequest request = CreateAccountRequest.builder().customerId(1L).initialCredit(BigDecimal.TEN).build();

        CreateAccountResponse response = accountService.create(request);

        Assertions.assertEquals(AccountStatus.CREATED, response.getAccountStatus());
    }


}
