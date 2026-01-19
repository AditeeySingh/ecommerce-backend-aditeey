package com.example.ecommerceAditeey.repository;

import com.example.ecommerceAditeey.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}