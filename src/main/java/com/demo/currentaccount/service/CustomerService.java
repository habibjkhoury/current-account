package com.demo.currentaccount.service;

import com.demo.currentaccount.dto.response.CustomerInquiryResponse;
import com.demo.currentaccount.entity.Account;
import com.demo.currentaccount.entity.Customer;
import com.demo.currentaccount.exception.CustomerNotFoundException;
import com.demo.currentaccount.repository.AccountRepository;
import com.demo.currentaccount.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final AccountRepository accountRepository;

    public Customer findCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with id {%s} does not exist", id)));
    }

    public CustomerInquiryResponse getCustomerDetails(Long id){
        Customer customer = findCustomerById(id);
        List<Account> accounts = accountRepository.findAccountsByCustomerId(id).orElse(null);

        return CustomerInquiryResponse
                .builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .accounts(accounts)
                .build();

    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

}
