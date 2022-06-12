package com.demo.currentaccount.controller;

import com.demo.currentaccount.common.model.CustomerNotFoundResponse;
import com.demo.currentaccount.dto.response.CustomerInquiryResponse;
import com.demo.currentaccount.service.CustomerService;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
@Slf4j
@Tag(name = "customer", description = "Customer API")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    @Operation(summary = "Get Customer Details", description = "Get customer details: name, surname, accounts, and the transactions of the accounts")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Successful"),
            @ApiResponse(responseCode = "400", description = "Bad request, customer does not exist", content = @Content(array = @ArraySchema(schema = @Schema(implementation = CustomerNotFoundResponse.class))))})
    public ResponseEntity<CustomerInquiryResponse> get(@PathVariable Long id) {
        CustomerInquiryResponse customerInquiryResponse = customerService.getCustomerDetails(id);
        log.info(customerInquiryResponse.toString());
        return new ResponseEntity<>(customerInquiryResponse, HttpStatus.OK);
    }

}
