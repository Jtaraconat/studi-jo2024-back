package com.jtaraconat.jo2024backend.Exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(long id ) {
        super("Could not found the cart with id: " + id);
    }
}
