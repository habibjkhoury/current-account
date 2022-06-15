package com.demo.currentaccount.service;

import com.demo.currentaccount.dto.request.CreateAccountRequest;
import com.demo.currentaccount.dto.response.CreateAccountResponse;

public interface AccountService {
    CreateAccountResponse create(CreateAccountRequest request);
}
