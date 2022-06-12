package com.demo.currentaccount.customers.controller;

import com.demo.currentaccount.controller.CustomerController;
import com.demo.currentaccount.dto.response.CustomerInquiryResponse;
import com.demo.currentaccount.service.CustomerService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @Test
    public void getCustomerById_ShouldReturnSuccess(){
        Mockito.when(customerService.getCustomerDetails(1L)).thenReturn(CustomerInquiryResponse.builder().build());

        ResponseEntity<CustomerInquiryResponse> responseEntity = customerController.get(1L);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}