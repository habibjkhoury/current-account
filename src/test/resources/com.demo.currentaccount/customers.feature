Feature: Get Customer Details

  Scenario: Get Customer Details for an existing customer
    Given a customer
    When we send a get details request for customer with id 1
    Then a response status 200 is received
    And customer details is received

  Scenario: Get Customer Details for a non existing customer
    Given a customer
    When we send a get details request for customer with id 7
    Then a response status 400 is received
    And e response error message is received
