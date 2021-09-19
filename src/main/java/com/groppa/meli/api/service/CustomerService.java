package com.groppa.meli.api.service;

import com.groppa.meli.api.Exceptions.ContentNotFoundException;
import com.groppa.meli.api.dto.CustomerBody;
import com.groppa.meli.api.dto.CustomerResponse;

public interface CustomerService {
    CustomerResponse createCustomer(CustomerBody customerBody);
    CustomerResponse updateCustomer(long id, CustomerBody customerBody) throws ContentNotFoundException;
    void deleteCustomer(long id) throws ContentNotFoundException;
}
