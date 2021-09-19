package com.groppa.meli.api.service;

import com.groppa.meli.api.Exceptions.ContentNotFoundException;
import com.groppa.meli.api.constants.Messages;
import com.groppa.meli.api.dto.CustomerBody;
import com.groppa.meli.api.dto.CustomerResponse;
import com.groppa.meli.api.model.Customer;
import com.groppa.meli.api.repository.CustomerRepository;
import com.groppa.meli.api.util.MapEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CustomerBody customerBody) {
        this.validateCustomer(customerBody);

        final Customer customer = MapEntity.map(customerBody, Customer.class);

        customer.setCreationDate(LocalDateTime.now());

        return createResponse(this.customerRepository.save(customer));
    }

    @Override
    public CustomerResponse updateCustomer(long id, CustomerBody customerBody) throws ContentNotFoundException {
        this.validateCustomer(customerBody);

        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new ContentNotFoundException(Messages.CUSTOMER_NOT_FOUND));

        MapEntity.map(customerBody, customer);

        return createResponse(this.customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(long id) throws ContentNotFoundException {
        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new ContentNotFoundException(Messages.CUSTOMER_NOT_FOUND));

        this.customerRepository.delete(customer);
    }


    private CustomerResponse createResponse(Customer customer) {
        return CustomerResponse
                .builder()
                .id(customer.getId())
                .build();
    }

    private void validateCustomer(CustomerBody customerBody) {
        Assert.notNull(customerBody, "CustomerBody must not be null");
        Assert.isTrue(!ObjectUtils.isEmpty(customerBody.getName()), "Customer name must not be empty");
    }
}
