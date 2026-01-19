package com.example.ecommerceAditeey.webhook;

import com.example.ecommerceAditeey.dto.PaymentWebhookRequest;
import com.example.ecommerceAditeey.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks/payment")
@RequiredArgsConstructor
public class PaymentWebhookController {

    private final PaymentService paymentService;

    @PostMapping
    public String handlePaymentWebhook(@RequestBody PaymentWebhookRequest request) {
        paymentService.handleWebhook(request);
        return "Webhook processed";
    }
}