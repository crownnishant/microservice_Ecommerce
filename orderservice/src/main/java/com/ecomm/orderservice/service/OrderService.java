package com.ecomm.orderservice.service;

import com.ecomm.orderservice.entity.Order;

import java.util.List;

public interface OrderService {

    //create order
    Order createOrder(Order order);

    //get all the orders
    List<Order> getAllOrders();

    //get single order
    Order getSingleOrder(Integer id);

    //update order
    Order updateOrder(Integer id);
}
