package com.jtaraconat.jo2024backend.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CartTest {

    private Cart cart;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUserId(1500);
        user.setFirstname("John");
        user.setLastname("Doe");

        cart = new Cart();
        cart.setCartId(1500);
        cart.setUser(user);
    }

    @Test
    public void testCartFields() {
        assertNotNull(cart.getCartId());
        assertEquals(user, cart.getUser());
    }
}
