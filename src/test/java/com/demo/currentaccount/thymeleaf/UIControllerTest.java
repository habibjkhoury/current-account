package com.demo.currentaccount.thymeleaf;

import com.demo.currentaccount.BaseTest;
import com.demo.currentaccount.controller.AccountController;
import com.demo.currentaccount.controller.UIController;
import com.demo.currentaccount.dto.request.CreateAccountRequest;
import com.demo.currentaccount.dto.response.CustomerInquiryResponse;
import com.demo.currentaccount.entity.Account;
import com.demo.currentaccount.entity.Customer;
import com.demo.currentaccount.entity.Transaction;
import com.demo.currentaccount.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

public class UIControllerTest extends BaseTest {

    @Mock
    AccountController accountController;

    @Mock
    CustomerServiceImpl customerService;

    @Mock
    Model model;

    @InjectMocks
    UIController uiController;

    static CustomerInquiryResponse customerInquiryResponse;

    @BeforeAll
    static void setup(){

        Transaction transaction = Transaction.builder().build();
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        Account account = Account.builder().transactions(transactions).build();
        List<Account> accounts = new ArrayList<>();
        accounts.add(account);
        customerInquiryResponse = CustomerInquiryResponse.builder()
                .id(1L)
                .name("name")
                .surname("surname")
                .accounts(accounts)
                .build();

    }

    @Test
    public void callView_withExistingCustomer_ShouldReturnViewName(){
        Mockito.when(customerService.getCustomerDetails(1L)).thenReturn(customerInquiryResponse);
        Assertions.assertEquals("transaction", uiController.view(model, 1L));
    }

    @Test
    public void createAccountButton_withExistingCustomer_ShouldRedirectView(){
        Mockito.when(accountController.create(Mockito.any(CreateAccountRequest.class))).thenReturn(null);
        CreateAccountRequest request = CreateAccountRequest.builder().customerId(1L).build();
        Assertions.assertEquals("redirect:/ui/v1/customer/1", uiController.create(request));
    }

    @Test
    public void callAccountView_ShouldReturnViewName(){
        Assertions.assertEquals("account", uiController.accountView(model, 1L));
    }

    @Test
    public void callCustomersView_ShouldReturnViewName(){
        List<Customer> customers = new ArrayList<>();
        Mockito.when(customerService.findAll()).thenReturn(customers);
        Assertions.assertEquals("customers", uiController.find(model));
    }
}
