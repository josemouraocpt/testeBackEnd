package com.example.testeiteam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.testeiteam.exception.ProductColletionException;
import com.example.testeiteam.model.Product;

import jakarta.validation.ConstraintViolationException;


@Service
public interface ProductService {
    public Product createProduct(Product product) throws ProductColletionException, ConstraintViolationException;

    public List<Product> getAllProducts();

    public Product getSingleProduct(Long id) throws ProductColletionException;

    public Product updateSingleProduct(Long id, Product product) throws ProductColletionException;

    public void deleteSingleProduct(Long id) throws ProductColletionException;
}
