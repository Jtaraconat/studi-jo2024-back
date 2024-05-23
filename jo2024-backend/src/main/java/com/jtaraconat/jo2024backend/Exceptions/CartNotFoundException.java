package com.jtaraconat.jo2024backend.Exceptions;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException(long id ) {
        super("Could not found the cart with id: " + id);
    }
}
