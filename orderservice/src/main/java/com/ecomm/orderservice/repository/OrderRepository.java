package com.ecomm.orderservice.repository;

import com.ecomm.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    //custom finder method
    List<Order> findByCustomerId(String customerId);

    List<Order> findByReference(String reference);

}
