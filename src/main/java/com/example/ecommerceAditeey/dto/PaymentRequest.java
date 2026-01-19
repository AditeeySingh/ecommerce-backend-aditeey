package com.example.ecommerceAditeey.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentRequest {

    @NotBlank
    private String orderId;

    @Positive
    private Double amount;
}