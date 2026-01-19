package com.example.ecommerceAditeey.service;

import com.example.ecommerceAditeey.dto.PaymentRequest;
import com.example.ecommerceAditeey.dto.PaymentWebhookRequest;
import com.example.ecommerceAditeey.model.Order;
import com.example.ecommerceAditeey.model.Payment;
import com.example.ecommerceAditeey.repository.OrderRepository;
import com.example.ecommerceAditeey.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    public Payment createPayment(PaymentRequest request) {

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getStatus().equals("CREATED")) {
            throw new RuntimeException("Order is not payable");
        }

        Payment payment = Payment.builder()
                .orderId(order.getId())
                .amount(request.getAmount())
                .status("PENDING")
                .paymentId("pay_mock_" + UUID.randomUUID())
                .createdAt(Instant.now())
                .build();

        payment = paymentRepository.save(payment);

        // 🔁 Simulate async payment gateway (3 sec delay)
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                handleWebhook(
                        new PaymentWebhookRequest() {{
                            setOrderId(order.getId());
                            setStatus("SUCCESS");
                        }}
                );
            } catch (InterruptedException ignored) {}
        }).start();

        return payment;
    }

    public void handleWebhook(PaymentWebhookRequest request) {

        Payment payment = paymentRepository.findByOrderId(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        payment.setStatus(request.getStatus());
        paymentRepository.save(payment);

        if (request.getStatus().equals("SUCCESS")) {
            order.setStatus("PAID");
        } else {
            order.setStatus("FAILED");
        }

        orderRepository.save(order);
    }
}