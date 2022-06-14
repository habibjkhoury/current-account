package com.demo.currentaccount.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CustomersStepDefs extends CucumberSpringConfiguration{


    @When("we send a get details request for customer with id {int}")
    public void weSendAGetRequestForCustomerWithId(int id) {
        sendGetCustomerDetailsRequest(id);
    }

    @Then("a response status {int} is received")
    public void aResponseStatusIsReceived(int statusCode) {
        assertThat(response.statusCode(), is(statusCode));
    }

    @And("customer details is received")
    public void customerDetailsIsReceived() {
        assertThat(response.path("id"), notNullValue());
    }


    @And("e response error message is received")
    public void eResponseErrorMessageIsReceived() {
        assertThat(response.path("message"), notNullValue());
    }
}
