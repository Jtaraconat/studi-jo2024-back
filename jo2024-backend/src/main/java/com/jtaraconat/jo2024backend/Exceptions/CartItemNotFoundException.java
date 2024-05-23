package com.jtaraconat.jo2024backend.Exceptions;

public class CartItemNotFoundException extends RuntimeException{
    public CartItemNotFoundException(int id ) {
        super("Could not found the cart item with id: " + id);
    }
}
