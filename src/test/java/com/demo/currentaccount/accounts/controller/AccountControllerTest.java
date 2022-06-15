package com.demo.currentaccount.accounts.controller;

import com.demo.currentaccount.BaseTest;
import com.demo.currentaccount.controller.AccountController;
import com.demo.currentaccount.dto.request.CreateAccountRequest;
import com.demo.currentaccount.dto.response.CreateAccountResponse;
import com.demo.currentaccount.enums.AccountStatus;
import com.demo.currentaccount.service.impl.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest extends BaseTest {

    @Mock
    AccountServiceImpl accountService;

    @InjectMocks
    AccountController accountController;

    static CreateAccountResponse createAccountResponse;

    @BeforeAll
    static void setup() {
        createAccountResponse = CreateAccountResponse.builder().accountId(1L).accountStatus(AccountStatus.CREATED).message("").build();
    }

    @Test
    public void createAccount_ExistingUser_ShouldReturnCreated() {

        CreateAccountRequest request = CreateAccountRequest.builder().customerId(1L).build();

        Mockito.when(accountService.create(request)).thenReturn(createAccountResponse);
        ResponseEntity<CreateAccountResponse> responseEntity = accountController.create(request);

        Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

}
