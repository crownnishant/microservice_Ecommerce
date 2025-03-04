package com.ecomm.customerservice.entities;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRecord customerRecord) {

        if(customerRecord==null){
            return null;
        }
        return Customer.builder()
                .id(customerRecord.id())
                .firstName(customerRecord.firstName())
                .lastName(customerRecord.lastName())
                .address(customerRecord.address())
                .email(customerRecord.email())
                .build();
    }

    public static CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
