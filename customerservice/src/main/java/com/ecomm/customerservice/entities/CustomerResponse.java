package com.ecomm.customerservice.entities;

public record CustomerResponse(

        String id,
        String firstName,
        String lastName,
        String email,
        Address address

) {
}
