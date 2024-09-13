package com.example.testeiteam.exception;

public class ProductColletionException extends Exception {

    public ProductColletionException(String message){
        super(message);
    }

    public static String NotFoundException(Long id) {
        return "Produto com o ID " + id.toString() + " não encontrado!";
    }
}
