package com.ecomm.customerservice.controller;

import com.ecomm.customerservice.entities.Customer;
import com.ecomm.customerservice.entities.CustomerResponse;
import com.ecomm.customerservice.service.CustomerService;
import com.ecomm.customerservice.entities.CustomerRecord;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerRecord customerRecord){
        return ResponseEntity.ok(customerService.createCustomer(customerRecord));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRecord customerRecord){
        customerService.updateCustomer(customerRecord);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomer(){
        return ResponseEntity.ok(customerService.findAllCustomer());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getSingleCustomer(@PathVariable String id){
            return ResponseEntity.ok(customerService.getSingleCustomer(id));


    }
}
