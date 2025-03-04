package com.ecomm.orderservice.Impl;

import com.ecomm.orderservice.DTO.PaymentRequest;
import com.ecomm.orderservice.DTO.PaymentResponse;
import com.ecomm.orderservice.entity.Order;
import com.ecomm.orderservice.entity.OrderLine;
import com.ecomm.orderservice.exception.BusinessException;
import com.ecomm.orderservice.exception.PaymentFailedException;
import com.ecomm.orderservice.exception.ProductOutOfStockException;
import com.ecomm.orderservice.feignclient.CustomerServiceClient;
import com.ecomm.orderservice.feignclient.NotificationServiceClient;
import com.ecomm.orderservice.feignclient.PaymentServiceClient;
import com.ecomm.orderservice.feignclient.ProductServiceClient;
import com.ecomm.orderservice.repository.OrderRepository;
import com.ecomm.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final OrderRepository orderRepository;
    private final CustomerServiceClient customerServiceClient;
    private final ProductServiceClient productServiceClient;
    private final PaymentServiceClient paymentServiceClient;
    private final NotificationServiceClient notificationServiceClient;


    @Override
    public Order createOrder(Order order) {
        //check the customer

        if(!customerServiceClient.existsById(order.getCustomerId())){
            throw new BusinessException("customer not found");
        }
        //purchase the product --> product microservices

        List<OrderLine> validOrderLines=new ArrayList<>();
        for(OrderLine orderLine: order.getOrderLines()){
            boolean isAvailable=productServiceClient.checkProductAvailability(orderLine.getProductId(),orderLine.getQty());
            if(!isAvailable){
                throw new ProductOutOfStockException("product id"+orderLine.getProductId()+" is out of stock");

            }
            BigDecimal productPrice=productServiceClient.getPrice(orderLine.getProductId());
            orderLine.setTotalAmount(productPrice);
            validOrderLines.add(orderLine);
        }
        //persist order

        order.setOrderLines(validOrderLines);
        order.setTotalAmount(validOrderLines.stream()
                .map(line->line.getTotalAmount().multiply(BigDecimal.valueOf(line.getQty())))
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        Order savedOrder=orderRepository.save(order);

        //start payment process

        PaymentRequest paymentRequest=new PaymentRequest(savedOrder.getId(), order.getTotalAmount(),order.getPaymentMethod());
        PaymentResponse paymentResponse=paymentServiceClient.processPayment(paymentRequest);
        if(!"Success".equals(paymentResponse.getStatus())){
            throw new PaymentFailedException("payment failed");
        }

        //send the order confirmation-->notification microservice
        notificationServiceClient.sendOrderNotification(order.getCustomerId(), savedOrder.getId());
        log.info("Order {} created successfully", savedOrder.getId());
        return savedOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        return List.of();
    }

    @Override
    public Order getSingleOrder(Integer id) {
        return null;
    }

    @Override
    public Order updateOrder(Integer id) {
        return null;
    }
}
