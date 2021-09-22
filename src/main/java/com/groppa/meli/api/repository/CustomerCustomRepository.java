package com.groppa.meli.api.repository;

import com.groppa.meli.api.collection.Customer;
import com.groppa.meli.api.dto.CustomerFilter;

import java.util.List;

public interface CustomerCustomRepository {
    List<Customer> getByFilter(CustomerFilter customerFilter);
}
