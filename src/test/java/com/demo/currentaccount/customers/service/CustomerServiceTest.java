package com.demo.currentaccount.customers.service;

import com.demo.currentaccount.entity.Customer;
import com.demo.currentaccount.exception.CustomerNotFoundException;
import com.demo.currentaccount.repository.CustomerRepository;
import com.demo.currentaccount.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @InjectMocks
    CustomerService customerService;

    Customer customer;

    @Before
    public void setup(){
        customer = Customer.builder()
                .id(1L)
                .build();
    }

    @Test
    public void findCustomerById_ExistingCustomer_ShouldSuccess(){
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));

        Customer customer1 = customerService.findCustomerById(1L);

        Assert.assertNotNull(customer1.getId());
    }

    @Test(expected = CustomerNotFoundException.class)
    public void findCustomerById_NonExistingCustomer_ShouldThrowExption(){
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        customerService.findCustomerById(1L);
    }
}
