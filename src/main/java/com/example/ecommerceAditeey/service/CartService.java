package com.example.ecommerceAditeey.service;

import com.example.ecommerceAditeey.dto.AddToCartRequest;
import com.example.ecommerceAditeey.model.CartItem;
import com.example.ecommerceAditeey.model.Product;
import com.example.ecommerceAditeey.repository.CartItemRepository;
import com.example.ecommerceAditeey.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartItem addToCart(AddToCartRequest request) {

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStock() < request.getQuantity()) {
            throw new RuntimeException("Not enough stock");
        }

        return cartItemRepository
                .findByUserIdAndProductId(request.getUserId(), request.getProductId())
                .map(cartItem -> {
                    cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
                    return cartItemRepository.save(cartItem);
                })
                .orElseGet(() -> {
                    CartItem item = CartItem.builder()
                            .userId(request.getUserId())
                            .productId(request.getProductId())
                            .quantity(request.getQuantity())
                            .build();
                    return cartItemRepository.save(item);
                });
    }

    public List<CartItem> getUserCart(String userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}