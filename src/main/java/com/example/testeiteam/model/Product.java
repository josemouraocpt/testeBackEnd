package com.example.testeiteam.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="products")

public class Product {
    @Id
    private Long id;
    @Indexed(unique=true)


    @NotNull(message= "O nome é obrigatório.")
    private String name;

    private String description;

    @NotNull(message= "O preço é obrigatório.")
    @Positive(message= "O preço deve ser positivo.")
    private BigDecimal price;

    @Positive(message= "A quantidade de estoque deve ser positiva.")
    private Integer stockQty;

    private OffsetDateTime createdAt;
    
    private OffsetDateTime updatedAt;
}
