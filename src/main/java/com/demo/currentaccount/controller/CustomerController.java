package com.demo.currentaccount.controller;

import com.demo.currentaccount.dto.response.CustomerInquiryResponse;
import com.demo.currentaccount.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public ResponseEntity<CustomerInquiryResponse> get(@PathVariable Long id) {
        return new ResponseEntity<>(customerService.getCustomerDetails(id), HttpStatus.OK);
    }
}
