package com.kodilla.ecommercee.exception;

public class CartItemNotFoundException extends Exception{
    public CartItemNotFoundException() {
        super("Cart item not found");
    }
}
