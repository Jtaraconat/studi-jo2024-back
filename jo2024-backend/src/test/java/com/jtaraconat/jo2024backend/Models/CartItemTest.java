package com.jtaraconat.jo2024backend.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartItemTest {
    private CartItem cartItem;
    private Cart cart;
    private Ticket ticket;

    @BeforeEach
    public void setUp() {
        cart = new Cart();
        cart.setCartId(1500);

        ticket = new Ticket();
        ticket.setTicketId(1500);
        ticket.setPrice(100);

        cartItem = new CartItem();
        cartItem.setCartItemId(1500);
        cartItem.setCart(cart);
        cartItem.setTicket(ticket);
        cartItem.setQuantity(2);
    }

    @Test
    public void testCartItemFields() {
        assertNotNull(cartItem.getCartItemId());
        assertEquals(cart, cartItem.getCart());
        assertEquals(ticket, cartItem.getTicket());
        assertEquals(2, cartItem.getQuantity());
    }
}
