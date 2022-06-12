package com.demo.currentaccount.exception.handler;

import com.demo.currentaccount.common.model.CustomerNotFoundResponse;
import com.demo.currentaccount.exception.CustomerNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = { CustomerNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(
            CustomerNotFoundException ex) {
        CustomerNotFoundResponse errorResponse = CustomerNotFoundResponse.builder().message(ex.getMessage()).build();
        return  ResponseEntity.badRequest().body(errorResponse);
    }
}
