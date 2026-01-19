package com.example.ecommerceAditeey.dto;

import lombok.Data;

@Data
public class PaymentWebhookRequest {
    private String orderId;
    private String status;
}