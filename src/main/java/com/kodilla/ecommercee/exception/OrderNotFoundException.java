package com.kodilla.ecommercee.exception;

public class OrderNotFoundException extends Exception{
    public OrderNotFoundException(){
        super("Order with given ID has not been found");
    }
}
