package com.groppa.meli.api.repository;

import com.groppa.meli.api.collection.Customer;
import com.groppa.meli.api.dto.CustomerFilter;
import com.groppa.meli.api.util.MapEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerCustomRepositoryImpl implements CustomerCustomRepository {

    private CustomerRepository customerRepository;
    public CustomerCustomRepositoryImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    @Override
    public List<Customer> getByFilter(CustomerFilter customerFilter) {
        ExampleMatcher caseInsensitive = ExampleMatcher.matchingAll().withIgnoreCase();
        final Customer customer = MapEntity.map(customerFilter, Customer.class);

        Example<Customer> example = Example.of(customer, caseInsensitive);

        return this.customerRepository.findAll(example);
    }
}
