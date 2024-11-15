package com.kodilla.ecommercee.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException() {
        super("Product with given ID has not been found");
    }
}
