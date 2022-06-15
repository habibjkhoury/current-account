package com.demo.currentaccount.controller;

import com.demo.currentaccount.dto.request.CreateAccountRequest;
import com.demo.currentaccount.dto.response.CustomerInquiryResponse;
import com.demo.currentaccount.entity.Customer;
import com.demo.currentaccount.model.TransactionDetails;
import com.demo.currentaccount.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/ui/v1")
public class UIController {

    private final CustomerService customerService;

    private final AccountController accountController;

    @GetMapping("/customer/{id}")
    public String  view(Model model, @PathVariable Long id) {
        CustomerInquiryResponse response = customerService.getCustomerDetails(id);

        List<TransactionDetails> detailsList = new ArrayList<>();

        response.getAccounts().forEach((account -> account.getTransactions().forEach(transaction -> detailsList.add(TransactionDetails.builder()
                .accountId(account.getId())
                .amount(transaction.getAmount())
                .accountBalance(account.getBalance())
                .id(transaction.getId()).build()))));

        model.addAttribute("customerId", id);
        model.addAttribute("customerName", response.getName());
        model.addAttribute("customerSurname", response.getSurname());
        model.addAttribute("transactions", detailsList);
        return "transaction";
    }

    @PostMapping("/accounts")
    public String create(@ModelAttribute("createAccountRequest")CreateAccountRequest createAccountRequest){
        accountController.create(createAccountRequest);
        return "redirect:/ui/v1/customer/" + createAccountRequest.getCustomerId();
    }

    @GetMapping ("/customers")
    public String find(Model model){
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/accounts/{id}")
    public String accountView(Model model, @PathVariable Long id){
        CreateAccountRequest createAccountRequest = CreateAccountRequest.builder().customerId(id).build();
        model.addAttribute("createAccountRequest", createAccountRequest);
        return "account";
    }
}
