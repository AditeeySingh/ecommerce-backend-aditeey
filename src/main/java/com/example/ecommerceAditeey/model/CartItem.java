package com.example.ecommerceAditeey.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "cart_items")
public class CartItem {

    @Id
    private String id;

    private String userId;
    private String productId;
    private Integer quantity;
}