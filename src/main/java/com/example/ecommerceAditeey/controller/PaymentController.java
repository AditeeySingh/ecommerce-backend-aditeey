package com.example.ecommerceAditeey.controller;

import com.example.ecommerceAditeey.dto.PaymentRequest;
import com.example.ecommerceAditeey.model.Payment;
import com.example.ecommerceAditeey.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public Payment createPayment(@Valid @RequestBody PaymentRequest request) {
        return paymentService.createPayment(request);
    }
}