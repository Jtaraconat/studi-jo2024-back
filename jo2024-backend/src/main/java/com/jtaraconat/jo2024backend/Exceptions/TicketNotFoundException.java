package com.jtaraconat.jo2024backend.Exceptions;

public class TicketNotFoundException extends RuntimeException{
    public TicketNotFoundException(int id ) {
        super("Could not found the ticket with id: " + id);
    }
}
