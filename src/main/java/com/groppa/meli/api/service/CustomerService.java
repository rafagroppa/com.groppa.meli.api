package com.groppa.meli.api.service;

import com.groppa.meli.api.Exceptions.ContentNotFoundException;
import com.groppa.meli.api.Exceptions.PreConditionException;
import com.groppa.meli.api.collection.Customer;
import com.groppa.meli.api.dto.CustomerBody;
import com.groppa.meli.api.dto.CustomerFilter;
import com.groppa.meli.api.dto.CustomerReport;
import com.groppa.meli.api.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerReport getById(long id) throws ContentNotFoundException;
    CustomerResponse createCustomer(CustomerBody customerBody);
    CustomerResponse updateCustomer(long id, CustomerBody customerBody) throws ContentNotFoundException;
    void deleteCustomer(long id) throws ContentNotFoundException;
    List<CustomerReport> getByFilter(CustomerFilter customerFilter) throws PreConditionException;
}
