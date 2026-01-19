package com.example.ecommerceAditeey.service;

import com.example.ecommerceAditeey.model.*;
import com.example.ecommerceAditeey.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public Order createOrder(String userId) {

        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double total = 0;

        // Stock check
        for (CartItem item : cartItems) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product");
            }

            total += product.getPrice() * item.getQuantity();
        }

        Order order = Order.builder()
                .userId(userId)
                .totalAmount(total)
                .status("CREATED")
                .createdAt(Instant.now())
                .build();

        order = orderRepository.save(order);

        // Create order items + reduce stock
        for (CartItem item : cartItems) {
            Product product = productRepository.findById(item.getProductId()).get();

            OrderItem orderItem = OrderItem.builder()
                    .orderId(order.getId())
                    .productId(product.getId())
                    .quantity(item.getQuantity())
                    .price(product.getPrice())
                    .build();

            orderItemRepository.save(orderItem);

            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }

        cartItemRepository.deleteByUserId(userId);

        return order;
    }

    public Order getOrder(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public List<OrderItem> getOrderItems(String orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
}