package com.demo.currentaccount.service;

import com.demo.currentaccount.entity.Customer;
import com.demo.currentaccount.exception.CustomerNotFoundException;
import com.demo.currentaccount.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer findCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with id {%s} does not exist", id)));
    }

}
