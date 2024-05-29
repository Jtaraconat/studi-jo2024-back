package com.jtaraconat.jo2024backend.Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OrderTest {
    private Order order;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setUserId(15);
        user.setFirstname("John");
        user.setLastname("Doe");

        order = new Order();
        order.setOrderId(15);
        order.setUser(user);

    }

    @Test
    public void testOrderFields() {
        assertNotNull(order.getOrderId());
        assertEquals(user, order.getUser());

    }
}
