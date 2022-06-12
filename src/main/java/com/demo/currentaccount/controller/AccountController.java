package com.demo.currentaccount.controller;

import com.demo.currentaccount.dto.request.CreateAccountRequest;
import com.demo.currentaccount.dto.response.CreateAccountResponse;
import com.demo.currentaccount.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<CreateAccountResponse> create(@Valid @RequestBody CreateAccountRequest request) {
        CreateAccountResponse response = accountService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
