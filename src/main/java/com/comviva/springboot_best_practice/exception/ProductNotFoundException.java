package com.comviva.springboot_best_practice.exception;


public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}
