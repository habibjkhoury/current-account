package com.demo.currentaccount.thymeleaf;

import com.demo.currentaccount.controller.AccountController;
import com.demo.currentaccount.controller.UIController;
import com.demo.currentaccount.dto.request.CreateAccountRequest;
import com.demo.currentaccount.dto.response.CustomerInquiryResponse;
import com.demo.currentaccount.entity.Account;
import com.demo.currentaccount.entity.Customer;
import com.demo.currentaccount.entity.Transaction;
import com.demo.currentaccount.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UIControllerTest {

    @Mock
    AccountController accountController;

    @Mock
    CustomerService customerService;

    @Mock
    Model model;

    @InjectMocks
    UIController uiController;

    CustomerInquiryResponse customerInquiryResponse;

    @Before
    public void setup(){

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
        Assert.assertEquals("transaction", uiController.view(model, 1L));
    }

    @Test
    public void createAccountButton_withExistingCustomer_ShouldRedirectView(){
        Mockito.when(accountController.create(Mockito.any(CreateAccountRequest.class))).thenReturn(null);
        CreateAccountRequest request = new CreateAccountRequest();
        request.setCustomerId(1L);
        Assert.assertEquals("redirect:/ui/v1/customer/1", uiController.create(request));
    }

    @Test
    public void callAccountView_ShouldReturnViewName(){
        Assert.assertEquals("account", uiController.accountView(model, 1L));
    }

    @Test
    public void callCustomersView_ShouldReturnViewName(){
        List<Customer> customers = new ArrayList<>();
        Mockito.when(customerService.findAll()).thenReturn(customers);
        Assert.assertEquals("customers", uiController.find(model));
    }
}
