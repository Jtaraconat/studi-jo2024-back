package com.jtaraconat.jo2024backend.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderItemTest {

    private OrderItem orderItem;
    private Order order;
    private Ticket ticket;

    @BeforeEach
    public void setUp() {
        order = new Order();
        order.setOrderId(15);

        ticket = new Ticket();
        ticket.setTicketId(15);
        ticket.setPrice(100);

        orderItem = new OrderItem();
        orderItem.setOrderItemId(15);
        orderItem.setOrder(order);
        orderItem.setTicket(ticket);
        orderItem.setQuantity(2);
        orderItem.setPrice(ticket.getPrice());
    }

    @Test
    public void testOrderItemFields() {
        assertNotNull(orderItem.getOrderItemId());
        assertEquals(order, orderItem.getOrder());
        assertEquals(ticket, orderItem.getTicket());
        assertEquals(2, orderItem.getQuantity());
        assertEquals(100.0, orderItem.getPrice());
    }
}
