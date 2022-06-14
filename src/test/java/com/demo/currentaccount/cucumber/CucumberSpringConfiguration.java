package com.demo.currentaccount.cucumber;

import com.demo.currentaccount.dto.request.CreateAccountRequest;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;

@CucumberContextConfiguration
@SpringBootTest(classes = CurrentAccountApplicationIntegrationTests.class)
public class CucumberSpringConfiguration {

    private @Value("${app.url}") String url;
    private @Value("${app.endpoint.accounts}") String accountsEndpoint;
    private @Value("${app.endpoint.customers}/") String customersEndpoint;

    protected Response response;

    protected void sendCreateAccountRequest(CreateAccountRequest request) {
        response =
                given()
                    .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(BIG_DECIMAL)))
                    .when()
                    .header("content-type", "application/json")
                    .body(request)
                    .post(url + accountsEndpoint)
                    .then()
                    .extract()
                    .response();
    }

    protected void sendGetCustomerDetailsRequest(int value) {
        response =
                given()
                        .contentType(ContentType.JSON)
                        .when()
                        .get(url + customersEndpoint + value)
                        .then()
                        .extract()
                        .response();
    }
}