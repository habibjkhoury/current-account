package com.demo.currentaccount.cucumber;

import com.demo.currentaccount.dto.request.CreateAccountRequest;
import com.demo.currentaccount.enums.AccountStatus;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class AccountsStepDefs extends CucumberSpringConfiguration {

  @Given("a customer")
  public void aCustomer() {
  }

  @When("we send a create account request for customer with id {long} with initial credit {bigdecimal}")
  public void weSendARequestWithId(long customerId, BigDecimal initialCredit) {
    CreateAccountRequest request = CreateAccountRequest.builder().customerId(customerId).initialCredit(initialCredit).build();
    sendCreateAccountRequest(request);
  }

  @Then("a status {int} is received")
  public void aStatusIsReceived(int statusCode) {
    assertThat(response.statusCode(), is(statusCode));
    assertThat(response.path("message"), notNullValue());
  }


  @Then("account successfully created")
  public void accountSuccessfullyCreated() {
    assertThat(response.path("accountStatus"), is(AccountStatus.CREATED.name()));
  }

  @Then("an error message is received")
  public void anErrorMessageIsReceived() {
    assertThat(response.path("message"), notNullValue());
  }

  @And("having balance of {bigdecimal}")
  public void havingBalanceOf(BigDecimal balance) {
    assertThat(response.path("balance"), is(balance));
  }
}
