package com.example.ecommerceAditeey.controller;

import com.example.ecommerceAditeey.dto.CreateOrderRequest;
import com.example.ecommerceAditeey.model.Order;
import com.example.ecommerceAditeey.model.OrderItem;
import com.example.ecommerceAditeey.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@Valid @RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request.getUserId());
    }

    @GetMapping("/{orderId}")
    public Map<String, Object> getOrder(@PathVariable String orderId) {
        Order order = orderService.getOrder(orderId);
        List<OrderItem> items = orderService.getOrderItems(orderId);

        Map<String, Object> response = new HashMap<>();
        response.put("order", order);
        response.put("items", items);

        return response;
    }
}