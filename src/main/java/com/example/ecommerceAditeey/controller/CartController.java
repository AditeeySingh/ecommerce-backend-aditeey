package com.example.ecommerceAditeey.controller;

import com.example.ecommerceAditeey.dto.AddToCartRequest;
import com.example.ecommerceAditeey.model.CartItem;
import com.example.ecommerceAditeey.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public CartItem addToCart(@Valid @RequestBody AddToCartRequest request) {
        return cartService.addToCart(request);
    }

    @GetMapping("/{userId}")
    public List<CartItem> getCart(@PathVariable String userId) {
        return cartService.getUserCart(userId);
    }

    @DeleteMapping("/{userId}/clear")
    public String clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
        return "Cart cleared successfully";
    }
}