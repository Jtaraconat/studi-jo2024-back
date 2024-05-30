package com.jtaraconat.jo2024backend.Models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketTest {

    @Test
    public void testGettersAndSetters() {
        // Create an instance of Ticket
        Ticket ticket = Ticket.builder()
                .ticketId(1)
                .eventName("Concert")
                .eventLocation("Venue")
                .time("7:00 PM")
                .day("Monday")
                .price(50)
                .image("image-url")
                .sport("Football")
                .city("Paris")
                .ticketType("Regular")
                .isActive(true)
                .build();

        // Test getters
        assertEquals(1, ticket.getTicketId());
        assertEquals("Concert", ticket.getEventName());
        assertEquals("Venue", ticket.getEventLocation());
        assertEquals("7:00 PM", ticket.getTime());
        assertEquals("Monday", ticket.getDay());
        assertEquals(50, ticket.getPrice());
        assertEquals("image-url", ticket.getImage());
        assertEquals("Football", ticket.getSport());
        assertEquals("Paris", ticket.getCity());
        assertEquals("Regular", ticket.getTicketType());
        assertEquals(true, ticket.isActive());

        // Test setters
        ticket.setTicketId(2);
        ticket.setEventName("Sports Event");
        ticket.setEventLocation("Stadium");
        ticket.setTime("2:00 PM");
        ticket.setDay("Saturday");
        ticket.setPrice(100);
        ticket.setImage("new-image-url");
        ticket.setSport("Basketball");
        ticket.setCity("New York");
        ticket.setTicketType("VIP");
        ticket.setIsActive(false);

        // Verify setters
        assertEquals(2, ticket.getTicketId());
        assertEquals("Sports Event", ticket.getEventName());
        assertEquals("Stadium", ticket.getEventLocation());
        assertEquals("2:00 PM", ticket.getTime());
        assertEquals("Saturday", ticket.getDay());
        assertEquals(100, ticket.getPrice());
        assertEquals("new-image-url", ticket.getImage());
        assertEquals("Basketball", ticket.getSport());
        assertEquals("New York", ticket.getCity());
        assertEquals("VIP", ticket.getTicketType());
        assertEquals(false, ticket.isActive());
    }
}
