package com.example.ecommerceAditeey.repository;

import com.example.ecommerceAditeey.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}