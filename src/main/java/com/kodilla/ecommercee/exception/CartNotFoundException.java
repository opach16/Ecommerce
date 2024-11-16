package com.kodilla.ecommercee.exception;

public class CartNotFoundException extends Exception  {
    public CartNotFoundException() {
        super("Cart with given ID has not been found");
    }
}
