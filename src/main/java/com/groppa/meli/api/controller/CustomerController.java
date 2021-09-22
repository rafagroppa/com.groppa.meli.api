package com.groppa.meli.api.controller;


import com.groppa.meli.api.collection.Customer;
import com.groppa.meli.api.constants.Urls;
import com.groppa.meli.api.dto.CustomerBody;
import com.groppa.meli.api.dto.CustomerFilter;
import com.groppa.meli.api.dto.CustomerReport;
import com.groppa.meli.api.dto.CustomerResponse;
import com.groppa.meli.api.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@Api(value = "Customer")
public class CustomerController extends BaseController{

    private CustomerService customerService;

    public CustomerController(final CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "Post Customer")
    @PostMapping(Urls.CUSTOMERS)
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerBody customerBody) {
        try {
            log.info("Starting method createCustomer");
            return new ResponseEntity<>(this.customerService.createCustomer(customerBody), HttpStatus.OK);
        } catch (Exception ex) {
            return buildResponseError(ex);
        }
    }

    @ApiOperation(value = "Get Customer by id")
    @GetMapping(Urls.CUSTOMER)
    public ResponseEntity<CustomerReport> getById(@PathVariable("id") long id) {
        try {
            log.info("Starting method getById");
            return new ResponseEntity<>(this.customerService.getById(id), HttpStatus.OK);
        } catch (Exception ex) {
            return buildResponseError(ex);
        }
    }

    @ApiOperation(value = "Put Customer")
    @PutMapping(Urls.CUSTOMER)
    public ResponseEntity<CustomerResponse> updateCustomer(@PathVariable("id") long id, @RequestBody CustomerBody customerBody) {
        try {
            log.info("Starting method updateCustomer");
            return new ResponseEntity<>(this.customerService.updateCustomer(id, customerBody), HttpStatus.OK);
        } catch (Exception ex) {
            return buildResponseError(ex);
        }
    }

    @ApiOperation(value = "Delete Customer")
    @DeleteMapping(Urls.CUSTOMER)
    public ResponseEntity deleteCustomer(@PathVariable long id) {
        try {
            log.info("Starting method deleteCustomer");
            this.customerService.deleteCustomer(id);

            log.info("Finishing method deleteCustomer");
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return buildResponseError(ex);
        }
    }

    @ApiOperation(value = "Get Customers by filter")
    @GetMapping(Urls.CUSTOMERS)
    public ResponseEntity<List<CustomerReport>> createCustomer(@RequestBody CustomerFilter customerFilter) {
        try {
            log.info("Starting method createCustomer");
            return new ResponseEntity<>(this.customerService.getByFilter(customerFilter), HttpStatus.OK);
        } catch (Exception ex) {
            return buildResponseError(ex);
        }
    }
}
