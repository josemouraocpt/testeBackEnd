package com.example.testeiteam.controller;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.testeiteam.model.Product;
import com.example.testeiteam.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest(controllers= ProductController.class)
@AutoConfigureMockMvc(addFilters=false)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService service;    

    @Autowired
    private ObjectMapper mapper;

    private Product product;

    @BeforeEach
    public void init(){
        product = new Product(1L, "Celular", "Samsung Galaxy A54", new BigDecimal(2), 2, OffsetDateTime.now(), null);
    }

    @Test
    void testCreateProduct() throws Exception {
        given(service.createProduct(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));
        ResultActions response = mvc.perform(post("/produtos")
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(product)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(product.getName())));
    }


    @Test
    void testGetAllProducts() throws Exception {
        List<Product> products = new ArrayList<Product>();
        products.add(product);
        when(service.getAllProducts()).thenReturn(products);
        ResultActions response = mvc.perform(get("/produtos").contentType(MediaType.APPLICATION_JSON));
        response.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(products.size())));
    }


    @Test
    void testGetProductById() throws Exception {
        Long id = 1L;
        when(service.getSingleProduct(id)).thenReturn(product);
        
        ResultActions response = mvc.perform(get("/produtos/"+id)
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(product)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(product.getName())));
    }


    @Test
    void testUpdateProductById() throws Exception {
        Long id = 1L;
        when(service.updateSingleProduct(id, product)).thenReturn(product);

        ResultActions response = mvc.perform(put("/produtos/"+id)
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(product)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
    }

    
    @Test
    void testDeleteProductById() throws Exception {
        Long id = 1L;
        doNothing().when(service).deleteSingleProduct(id);

        ResultActions response = mvc.perform(delete("/produtos/"+id)
        .contentType(MediaType.TEXT_PLAIN));

        response.andExpect(MockMvcResultMatchers.status().isOk())
        .andDo(MockMvcResultHandlers.print());
    }
}
