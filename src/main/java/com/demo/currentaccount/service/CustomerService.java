package com.demo.currentaccount.service;

import com.demo.currentaccount.dto.response.CustomerInquiryResponse;
import com.demo.currentaccount.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer findCustomerById(Long id);

    CustomerInquiryResponse getCustomerDetails(Long id);

    List<Customer> findAll();
}
