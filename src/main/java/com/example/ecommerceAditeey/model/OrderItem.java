package com.example.ecommerceAditeey.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "order_items")
public class OrderItem {

    @Id
    private String id;

    private String orderId;
    private String productId;
    private Integer quantity;
    private Double price;
}