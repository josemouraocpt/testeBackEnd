package com.example.testeiteam.service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.example.testeiteam.exception.ProductColletionException;
import com.example.testeiteam.model.Product;
import com.example.testeiteam.repository.ProductRepository;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepo;    

    @InjectMocks
    private ProductServiceImpl service;
    

    @Test
    void testCreateProduct() throws ProductColletionException {
        Product product = new Product(1L, "Celular", "Samsung Galaxy A54", new BigDecimal(2), 2, OffsetDateTime.now(), null);
        when(productRepo.save(Mockito.any(Product.class))).thenReturn(product);
        Product newProduct = service.createProduct(product);
        Assertions.assertThat(newProduct).isNotNull();
    }


    @Test
    void testGetAllProducts() throws ProductColletionException {
        List<Product> products = Mockito.mock(List.class);
        when(productRepo.findAll()).thenReturn(products);
        List<Product> productsRes = service.getAllProducts();
        Assertions.assertThat(productsRes).isNotEqualTo(0);
    }
    

    @Test
    void testGetSingleProduct() throws ProductColletionException {
        Product product = new Product(1L, "Celular", "Samsung Galaxy A54", new BigDecimal(2), 2, OffsetDateTime.now(), null);
        String productId = product.getId().toString();
        when(productRepo.findById(productId)).thenReturn(Optional.ofNullable(product));
        Product existsProduct = service.getSingleProduct(product.getId());
        Assertions.assertThat(existsProduct).isNotNull();
    }
    

    @Test
    void testUpdateSingleProduct() throws ProductColletionException {
        Product product = new Product(1L, "Celular", "Samsung Galaxy A54", new BigDecimal(2), 2, OffsetDateTime.now(), OffsetDateTime.now());
        String productId = product.getId().toString();
        when(productRepo.findById(productId)).thenReturn(Optional.ofNullable(product));
        when(productRepo.save(Mockito.any(Product.class))).thenReturn(product);
        Product savedProduct = service.updateSingleProduct(product.getId(), product);
        Assertions.assertThat(savedProduct).isNotNull();
    }


    @Test
    void testDeleteSingleProduct() throws ProductColletionException {
        Product product = new Product(1L, "Celular", "Samsung Galaxy A54", new BigDecimal(2), 2, OffsetDateTime.now(), null);
        String productId = product.getId().toString();
        when(productRepo.findById(productId)).thenReturn(Optional.ofNullable(product));
        assertAll(() -> service.deleteSingleProduct(product.getId()));
    }
}
