package com.groppa.meli.api.service;

import com.groppa.meli.api.exceptions.ContentNotFoundException;
import com.groppa.meli.api.exceptions.PreConditionException;
import com.groppa.meli.api.constants.Messages;
import com.groppa.meli.api.dto.CustomerBody;
import com.groppa.meli.api.dto.CustomerFilter;
import com.groppa.meli.api.dto.CustomerReport;
import com.groppa.meli.api.dto.CustomerResponse;
import com.groppa.meli.api.collection.Customer;
import com.groppa.meli.api.repository.CustomerCustomRepository;
import com.groppa.meli.api.repository.CustomerRepository;
import com.groppa.meli.api.util.MapEntity;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerCustomRepository customerCustomRepository;

    public CustomerServiceImpl(final CustomerRepository customerRepository,
                               final CustomerCustomRepository customerCustomRepository) {
        this.customerRepository = customerRepository;
        this.customerCustomRepository = customerCustomRepository;
    }

    @Override
    public CustomerReport getById(long id) throws ContentNotFoundException {
        log.info("Starting method getById");

        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new ContentNotFoundException(Messages.CUSTOMER_NOT_FOUND));

        log.info("Finishing method getById");
        return MapEntity.map(customer, CustomerReport.class);
    }

    @Override
    public CustomerResponse createCustomer(CustomerBody customerBody) {
        log.info("Starting method createCustomer");
        this.validateCustomer(customerBody);

        final Customer customer = MapEntity.map(customerBody, Customer.class);

        customer.setCreationDate(LocalDateTime.now());

        log.info("Finishing method createCustomer");
        return createResponse(this.customerRepository.save(customer));
    }

    @Override
    public CustomerResponse updateCustomer(long id, CustomerBody customerBody) throws ContentNotFoundException {
        log.info("Starting method updateCustomer");
        this.validateCustomer(customerBody);

        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new ContentNotFoundException(Messages.CUSTOMER_NOT_FOUND));

        MapEntity.map(customerBody, customer);

        log.info("Finishing method updateCustomer");
        return createResponse(this.customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(long id) throws ContentNotFoundException {
        log.info("Starting method deleteCustomer");
        Customer customer = this.customerRepository.findById(id).orElseThrow(() -> new ContentNotFoundException(Messages.CUSTOMER_NOT_FOUND));

        this.customerRepository.delete(customer);

        log.info("Finishing method deleteCustomer");
    }

    @Override
    public List<CustomerReport> getByFilter(CustomerFilter customerFilter) throws PreConditionException {
        log.info("Starting method getByFilter");

        if (customerFilter == null) {
            throw new PreConditionException(Messages.INVALID_FILTER);
        }

        final List<Customer> customers = this.customerCustomRepository.getByFilter(customerFilter);

        return MapEntity.map(customers, new TypeToken<List<CustomerReport>>() {}.getType());
    }


    private CustomerResponse createResponse(Customer customer) {
        return CustomerResponse
                .builder()
                .id(customer.getId())
                .build();
    }

    private void validateCustomer(CustomerBody customerBody) {
        log.info("Starting method validateCustomer");
        Assert.notNull(customerBody, "CustomerBody must not be null");
        Assert.isTrue(!ObjectUtils.isEmpty(customerBody.getName()), "Customer name must not be empty");
        log.info("Finishing method validateCustomer");
    }
}
