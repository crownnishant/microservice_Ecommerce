package com.ecomm.customerservice.Impl;

import com.ecomm.customerservice.Repository.CustomerRepository;
import com.ecomm.customerservice.entities.CustomerMapper;
import com.ecomm.customerservice.entities.CustomerRecord;
import com.ecomm.customerservice.entities.CustomerResponse;
import com.ecomm.customerservice.exception.CustomerNotFoundException;
import com.ecomm.customerservice.service.CustomerService;
import com.ecomm.customerservice.entities.Customer;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    @Override
    public Customer createCustomer(CustomerRecord customerRecord) {
        Customer customer=mapper.toCustomer(customerRecord);
        customer.setId(UUID.randomUUID().toString());
        return customerRepository.save(customer);
        //return save.getId();
    }

    @Override
    public List<CustomerResponse> findAllCustomer() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::fromCustomer)
                .toList();
    }

    @Override
    public CustomerResponse getSingleCustomer(String id) {
        Customer singleCustomer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("customer not found with given id"));
        return CustomerMapper.fromCustomer(singleCustomer);

    }

    @Override
    public void updateCustomer(CustomerRecord customerRecord) {
        Customer existingCustomer = customerRepository.findById(customerRecord.id()).orElseThrow(() -> new CustomerNotFoundException("customer not found with the given id"));

        if(StringUtils.isNotBlank(customerRecord.firstName())){
            existingCustomer.setFirstName(customerRecord.firstName());
        }

        if(StringUtils.isNotBlank(customerRecord.lastName())){
            existingCustomer.setLastName(customerRecord.lastName());
        }

        if(StringUtils.isNotBlank(customerRecord.email())){
            existingCustomer.setEmail(customerRecord.email());
        }

        if(customerRecord.address()!=null){
            existingCustomer.setAddress(customerRecord.address());
        }
        customerRepository.save(existingCustomer);
    }
}
