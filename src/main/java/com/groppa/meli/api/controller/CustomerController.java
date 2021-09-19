package com.groppa.meli.api.controller;

import com.groppa.meli.api.Exceptions.ContentNotFoundException;
import com.groppa.meli.api.constants.Urls;
import com.groppa.meli.api.dto.CustomerBody;
import com.groppa.meli.api.dto.CustomerResponse;
import com.groppa.meli.api.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController extends BaseController{

    private CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(Urls.CUSTOMERS)
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerBody customerBody) {
        try {
            return new ResponseEntity<>(this.customerService.createCustomer(customerBody), HttpStatus.OK);
        } catch (Exception ex) {
            return buildResponseError(ex);
        }
    }

    @PutMapping(Urls.CUSTOMER)
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable("id") long id, @RequestBody CustomerBody customerBody) {
        try {
            return new ResponseEntity<>(this.customerService.updateCustomer(id, customerBody), HttpStatus.OK);
        } catch (Exception ex) {
            return buildResponseError(ex);
        }
    }

    @DeleteMapping(Urls.CUSTOMER)
    public ResponseEntity deleteCustomer(@PathVariable long id) {
        try {
            this.customerService.deleteCustomer(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return buildResponseError(ex);
        }
    }
}
