package com.jtaraconat.jo2024backend.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(long id ) {
        super("Could not found the user with id: " + id);
    }
}
