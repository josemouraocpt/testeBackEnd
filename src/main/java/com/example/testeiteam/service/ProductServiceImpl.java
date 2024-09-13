package com.example.testeiteam.service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testeiteam.exception.ProductColletionException;
import com.example.testeiteam.model.Product;
import com.example.testeiteam.repository.ProductRepository;

import jakarta.validation.ConstraintViolationException;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Override
    public Product createProduct(Product product) throws ProductColletionException, ConstraintViolationException {
       product.setCreatedAt(OffsetDateTime.now());
       product.setId(System.currentTimeMillis());
       productRepo.save(product);
       return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepo.findAll();
        if(products.size() > 0){
            return  products;
        }else{
            return new ArrayList<Product>();
        }
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductColletionException {
        String strId = id.toString();
        Optional<Product> optionalProduct = productRepo.findById(strId);
        if(!optionalProduct.isPresent()){
            throw new ProductColletionException(ProductColletionException.NotFoundException(id)); 
        }else{
            return optionalProduct.get();
        }
    }

    @Override
    public Product updateSingleProduct(Long id, Product product) throws ProductColletionException {
        String strId = id.toString();
        Optional<Product> optionalProduct = productRepo.findById(strId);
        if(optionalProduct.isPresent()){
            Product productToUpdate = optionalProduct.get();
            productToUpdate.setName(product.getName());
            productToUpdate.setDescription(product.getDescription());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setStockQty(product.getStockQty());
            productToUpdate.setUpdatedAt(OffsetDateTime.now());
            productRepo.save(productToUpdate);
            return productToUpdate;
        }else{
            throw new ProductColletionException(ProductColletionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteSingleProduct(Long id) throws ProductColletionException {
        String strId = id.toString();
        Optional<Product> optionalProduct = productRepo.findById(strId);
        if(optionalProduct.isPresent()){
            productRepo.deleteById(strId);
        }else{
            throw new ProductColletionException(ProductColletionException.NotFoundException(id));
        }
    }

}
