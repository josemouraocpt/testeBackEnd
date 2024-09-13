package com.example.testeiteam.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.testeiteam.model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}
