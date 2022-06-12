package com.demo.currentaccount.accounts.controller;

import com.demo.currentaccount.controller.AccountController;
import com.demo.currentaccount.dto.request.CreateAccountRequest;
import com.demo.currentaccount.dto.response.CreateAccountResponse;
import com.demo.currentaccount.enums.AccountStatus;
import com.demo.currentaccount.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

    @Mock
    AccountService accountService;

    @InjectMocks
    AccountController accountController;

    CreateAccountResponse createAccountResponse;

    @Before
    public void setup() {
        createAccountResponse = CreateAccountResponse.builder().accountId(1L).accountStatus(AccountStatus.CREATED).message("").build();
    }

    @Test
    public void createAccount_ExistingUser_ShouldReturnCreated() {
        CreateAccountRequest request = new CreateAccountRequest();

        request.setCustomerId(1L);

        Mockito.when(accountService.create(request)).thenReturn(createAccountResponse);
        ResponseEntity<CreateAccountResponse> responseEntity = accountController.create(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

}
