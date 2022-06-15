package com.demo.currentaccount.controller;

import com.demo.currentaccount.common.model.CustomerNotFoundResponse;
import com.demo.currentaccount.dto.request.CreateAccountRequest;
import com.demo.currentaccount.dto.response.CreateAccountResponse;
import com.demo.currentaccount.service.impl.AccountServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Tag(name = "account", description = "Account API")
public class AccountController {

    private final AccountServiceImpl accountService;

    @PostMapping
    @Operation(summary = "Create new account", description = "Create new account and initiate transaction if initial credit available")
    @ApiResponses(value = {
            @ApiResponse(responseCode="201", description = "Account created"),
    @ApiResponse(responseCode = "400", description = "Bad request, customer does not exist", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CustomerNotFoundResponse.class))))})
    public ResponseEntity<CreateAccountResponse> create(@Valid @RequestBody CreateAccountRequest request) {
        log.info("creating new account");
        CreateAccountResponse response = accountService.create(request);
        log.info(response.toString());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
