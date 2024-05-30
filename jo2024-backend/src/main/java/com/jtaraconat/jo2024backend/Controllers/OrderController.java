package com.jtaraconat.jo2024backend.Controllers;

import com.google.zxing.WriterException;
import com.jtaraconat.jo2024backend.DTO.OrderItemDTO;
import com.jtaraconat.jo2024backend.Models.Order;
import com.jtaraconat.jo2024backend.Repositories.OrderItemRepository;
import com.jtaraconat.jo2024backend.Services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = {"https://studi-jo2024.web.app"})
@Tag(name ="Order API", description = "API for managing orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemRepository orderItemRepository;


    @PostMapping("/api/order/create/{userId}")
    @Operation(method = "POST", summary = "Create an order", description = "Allows the user to create an order from his cart")
    public ResponseEntity<Order> createOrder(@PathVariable int userId) {
        try {
            Order order = orderService.createOrderFromCart(userId);
            return ResponseEntity.ok(order);
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("api/order/orderitems/all")
    @Operation(method = "GET", summary = "Get all order items", description = "Allows the frontend to get all order items in a user cart")
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        try {
            List<OrderItemDTO> orderItems = orderService.getAllOrderItems();
            return ResponseEntity.ok(orderItems);
        } catch(Exception e ) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/api/order/qrcode/{orderItemId}")
    @Operation(method = "GET", summary = "Get the QRCode", description = "Allows the frontend to get the QRCode for an orderItem")
    public ResponseEntity<String> getQRCodeForOrderItem(@PathVariable int orderItemId) {
        try {
            String qrCode = orderService.getQRCodeForOrderItem(orderItemId);
            return ResponseEntity.ok(qrCode);
        } catch (WriterException | IOException e) {
            return ResponseEntity.status(500).body("Error generating QR code");
        }
    }

}
