package com.demo.currentaccount.accounts.service;

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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    @Mock
    AccountRepository accountRepository;

    @Mock
    CustomerService customerService;

    @Mock
    TransactionService transactionService;

    Account account;

    Customer customer;

    InitiateTransactionResponse transactionResponse;

    @Before
    public void setup() {
        customer = Customer.builder().id(1L).build();
        account = Account.builder().id(1L).balance(BigDecimal.ZERO).customer(customer).build();
        transactionResponse = InitiateTransactionResponse.builder().amount(BigDecimal.ZERO).status(TransactionStatus.APPROVED).build();
    }

    @InjectMocks
    AccountService accountService;

    @Test
    public void createNewAccount_ExistingCustomerZeroBalance_ShouldReturnCreated() {

        Mockito.when(customerService.findCustomerById(1L)).thenReturn(customer);
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);

        CreateAccountRequest request = new CreateAccountRequest();

        request.setCustomerId(1L);
        CreateAccountResponse response = accountService.create(request);

        assertEquals(AccountStatus.CREATED, response.getAccountStatus());
    }

    @Test
    public void createNewAccount_ExistingCustomerWithBalance_ShouldReturnCreated() {

        Mockito.when(customerService.findCustomerById(1L)).thenReturn(customer);
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account);
        Mockito.when(transactionService.initiate(Mockito.any(Account.class), Mockito.any(BigDecimal.class))).thenReturn(transactionResponse);

        CreateAccountRequest request = new CreateAccountRequest();

        request.setCustomerId(1L);
        request.setInitialCredit(BigDecimal.TEN);
        CreateAccountResponse response = accountService.create(request);

        assertEquals(AccountStatus.CREATED, response.getAccountStatus());
    }


}
