package com.example.microservice.order.service;

import com.example.microservice.order.client.InventoryClient;
import com.example.microservice.order.dto.OrderRequest;
import com.example.microservice.order.model.Order;
import com.example.microservice.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {
         var isProductInStock = inventoryClient.isInStock(orderRequest.stuCode(), orderRequest.quantity());
         if(isProductInStock) {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setStuCode(orderRequest.stuCode());
            order.setPrice(orderRequest.price());
            order.setQuantity(orderRequest.quantity());
            orderRepository.save(order);
        } else {
            throw new RuntimeException("Product is out of stock for stuCode : " + orderRequest.stuCode());
        }
    }
}
