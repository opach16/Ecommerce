package com.kodilla.ecommercee.exception;

public class OrderNotFoundException extends Exception{
    public OrderNotFoundException(){
        super("Order with give Id has not been found");
    }
}
