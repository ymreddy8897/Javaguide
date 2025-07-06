package com.comviva.springboot_best_practice.exception;

public class ProductServiceBusinessException extends RuntimeException{

    public ProductServiceBusinessException(String message) {
        super(message);
    }

}
