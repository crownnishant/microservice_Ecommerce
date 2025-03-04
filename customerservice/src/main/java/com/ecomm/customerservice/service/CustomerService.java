package com.ecomm.customerservice.service;

import com.ecomm.customerservice.entities.Customer;
import com.ecomm.customerservice.entities.CustomerRecord;
import com.ecomm.customerservice.entities.CustomerResponse;

import java.util.List;

public interface CustomerService {

    //create customer
    Customer createCustomer(CustomerRecord customerRecord);

    //get all customers
    List<CustomerResponse> findAllCustomer();

    //get single customer
    CustomerResponse getSingleCustomer(String id);

    void updateCustomer(CustomerRecord customerRecord);


}
