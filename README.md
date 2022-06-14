# CURRENT-ACCOUNT

-  an API to be used for opening a new “current account” of already existing
   customers. The API exposes an endpoint which accepts the user information (customerID,
   initialCredit).

`/api/v1/accounts`

*Once the endpoint is called, a new account will be opened connected to the user whose ID is
customerID.  Also, if initialCredit is not 0, a transaction will be sent to the new account.*


-  Another Endpoint will output the user information showing Name, Surname, balance, and
   transactions of the accounts.

`api/v1/customers/{id}`

the APIs have a UI which can be accessed using:

`ui/v1/customers`

the UI consists of the list of already existing customers, where we can list their transactions(accounts & transaction) using List Transactions Button and add a new account from the transactions screen using Add Account Button precising the initial credit and pressing Create Account button.

if initial credit not 0 a new transaction will initiate and will be shown in the table list of transactions

## other details:

- github actions which will build test and deploy the application on heroku when merging or pushing to master:

`https://current-account-heroku-prod.herokuapp.com/`

- configured docker-compose and Dockerfile to be able to build a docker image of the application using this command:

`docker-compose up`

- swagger documentation which can be accessed using the below endpoint:

`/swagger-ui.html`

- integration-testing using cucumber that can be executed using `mvn clean test` which will run unit and integration testing. Please note that the application should already be started locally for the integration tests to run.
*For integration testing if application started on a port other than 8080 make sure to change the port in src/test/resources/application.properties
*For the unit test run alone `mvn clean test -Punit-test` should be used*

- the application can be started by runing the maven command `mvn spring-boot:run` or by running CurrentAccountApplication.class from an IDE

- H2 in memory database with data inserted on startup as below:

> `INSERT INTO CUSTOMER(ID, NAME, SURNAME) VALUES (1, 'MYNAME', 'MYSURNAME');`
> `INSERT INTO CUSTOMER(ID, NAME, SURNAME) VALUES (2, 'MYNAME2', 'MYSURNAME2');`
> `INSERT INTO CUSTOMER(ID, NAME, SURNAME) VALUES (3, 'MYNAME3', 'MYSURNAME3');`
>
> `INSERT INTO ACCOUNT(BALANCE, CUSTOMER_ID) VALUES (10, 1);`
> `INSERT INTO ACCOUNT(BALANCE, CUSTOMER_ID) VALUES (30, 1);`
> `INSERT INTO ACCOUNT(BALANCE, CUSTOMER_ID) VALUES (20, 2);`
> `INSERT INTO ACCOUNT(BALANCE, CUSTOMER_ID) VALUES (40, 2);`
> `INSERT INTO ACCOUNT(BALANCE, CUSTOMER_ID) VALUES (30, 3);`
>
> `INSERT INTO TRANSACTION (AMOUNT, ACCOUNT_ID) VALUES (10, 1);`
> `INSERT INTO TRANSACTION (AMOUNT, ACCOUNT_ID) VALUES (30, 2);`
> `INSERT INTO TRANSACTION (AMOUNT, ACCOUNT_ID) VALUES (20, 3);`
> `INSERT INTO TRANSACTION (AMOUNT, ACCOUNT_ID) VALUES (40, 4);`
> `INSERT INTO TRANSACTION (AMOUNT, ACCOUNT_ID) VALUES (30, 5);`
