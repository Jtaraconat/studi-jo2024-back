package com.jtaraconat.jo2024backend.DTO;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class OrderItemDTOTest {
    @Test
    public void testGettersAndSetters() {
        // Create an instance of OrderItemDTO
        OrderItemDTO orderItemDTO = new OrderItemDTO(1, 2, 3, 100, 2, true);

        // Test getters
        assertEquals(1, orderItemDTO.getOrderItemId());
        assertEquals(2, orderItemDTO.getOrderId());
        assertEquals(3, orderItemDTO.getTicketId());
        assertEquals(100, orderItemDTO.getPrice());
        assertEquals(2, orderItemDTO.getQuantity());
        assertEquals(true, orderItemDTO.isActive());

        // Test setters
        orderItemDTO.setOrderItemId(10);
        orderItemDTO.setOrderId(20);
        orderItemDTO.setTicketId(30);
        orderItemDTO.setPrice(200);
        orderItemDTO.setQuantity(3);
        orderItemDTO.setActive(false);

        // Verify setters
        assertEquals(10, orderItemDTO.getOrderItemId());
        assertEquals(20, orderItemDTO.getOrderId());
        assertEquals(30, orderItemDTO.getTicketId());
        assertEquals(200, orderItemDTO.getPrice());
        assertEquals(3, orderItemDTO.getQuantity());
        assertEquals(false, orderItemDTO.isActive());
    }
}
