package com.jtaraconat.jo2024backend.Controllers;

import com.jtaraconat.jo2024backend.DTO.OrderItemDTO;
import com.jtaraconat.jo2024backend.Models.Order;
import com.jtaraconat.jo2024backend.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/api/order/create/{userId}")
    public ResponseEntity<Order> createOrder(@PathVariable int userId) {
        try {
            Order order = orderService.createOrderFromCart(userId);
            return ResponseEntity.ok(order);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("api/order/orderitems/all")
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        try {
            List<OrderItemDTO> orderItems = orderService.getAllOrderItems();
            return ResponseEntity.ok(orderItems);
        } catch(Exception e ) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
