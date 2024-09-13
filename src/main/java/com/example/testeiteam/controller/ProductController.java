package com.example.testeiteam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.testeiteam.exception.ProductColletionException;
import com.example.testeiteam.model.Product;
import com.example.testeiteam.service.ProductService;

import jakarta.validation.ConstraintViolationException;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/produtos")
    public ResponseEntity<?> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<List<Product>>(products, products.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/produtos")
    public ResponseEntity<?> createProduct(@RequestBody Product product){
        try {
            productService.createProduct(product);
            return new ResponseEntity<Product>(product, HttpStatus.CREATED);
        } 
        catch (ProductColletionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } 
        catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>(productService.getSingleProduct(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<?> updateProductById(@PathVariable("id") Long id, @RequestBody Product product){
        try {
            productService.updateSingleProduct(id, product);
            return new ResponseEntity<>("Produto atualizado com sucesso!"  , HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch(ProductColletionException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id){
        try {
            productService.deleteSingleProduct(id);
            return new ResponseEntity<>("Produto deletado com sucesso!", HttpStatus.OK);
        } catch (ProductColletionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
