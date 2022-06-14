
Feature: Create Account API

  Scenario: Create account for existing customer without balance
    Given a customer
    When we send a create account request for customer with id 1 with initial credit 0.0
    Then a status 201 is received
    And account successfully created

  Scenario: Create account for non existing customer
    Given a customer
    When we send a create account request for customer with id 7 with initial credit 0.0
    Then a status 400 is received
    And an error message is received

  Scenario: Create account for existing customer with balance
    Given a customer
    When we send a create account request for customer with id 1 with initial credit 10.0
    Then a status 201 is received
    And account successfully created
    And having balance of 10.0


#  Scenario: return a null pointerException
#    Given a user
#    When we send a get request to the error endpoint
#    Then a status 400 is received
#    And error message "invalid format" is returned