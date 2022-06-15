package com.demo.currentaccount.customers.service;

import com.demo.currentaccount.BaseTest;
import com.demo.currentaccount.entity.Customer;
import com.demo.currentaccount.exception.CustomerNotFoundException;
import com.demo.currentaccount.repository.CustomerRepository;
import com.demo.currentaccount.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

public class CustomerServiceTest extends BaseTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerServiceImpl customerService;

    static Customer customer;

    @BeforeAll
    static void setup(){
        customer = Customer.builder()
                .id(1L)
                .build();
    }

    @Test
    public void findCustomerById_ExistingCustomer_ShouldSuccess(){
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));

        Customer customer1 = customerService.findCustomerById(1L);

        Assertions.assertNotNull(customer1.getId());
    }

    @Test
    public void findCustomerById_NonExistingCustomer_ShouldThrowExption(){
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(CustomerNotFoundException.class, () -> customerService.findCustomerById(1L));

    }
}
