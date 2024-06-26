package com.jtaraconat.jo2024backend.Services;

import com.google.zxing.WriterException;
import com.jtaraconat.jo2024backend.DTO.OrderItemDTO;
import com.jtaraconat.jo2024backend.Exceptions.CartNotFoundException;
import com.jtaraconat.jo2024backend.Models.*;
import com.jtaraconat.jo2024backend.Repositories.CartItemRepository;
import com.jtaraconat.jo2024backend.Repositories.CartRepository;
import com.jtaraconat.jo2024backend.Repositories.OrderItemRepository;
import com.jtaraconat.jo2024backend.Repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private QRCodeGenerator qrCodeGenerator;


    @Transactional
    public Order createOrderFromCart(int userId) throws SQLException, IOException, WriterException {
        Cart cart = cartRepository.findByUserUserId(userId);
        if(cart == null) {
            throw  new CartNotFoundException(userId);
        }

        List<CartItem> cartItems = cartItemRepository.findByCartCartId(cart.getCartId());
        if (cartItems.isEmpty()) {
            throw new RuntimeException("cart is empty");
        }

        User user = cart.getUser();
        UUID userUUID = user.getUniqueUserKey();

        Order order = new Order();
        order.setUser(cart.getUser());
        order = orderRepository.save(order);

        String qrCode = "";


        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setTicket(cartItem.getTicket());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getTicket().getPrice());
            orderItem.setSecurityKey(orderItem.getUniqueOrderItemKey().toString() + "-" + userUUID.toString());
            orderItem.setQRCode(QRCodeGenerator.generateQRCodeBase64(orderItem.getSecurityKey(), 300, 300));
            orderItemRepository.save(orderItem);
        }
        return order;
    }

    public List<OrderItemDTO> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream()
                .map(orderItem -> new OrderItemDTO(
                        orderItem.getOrderItemId(),
                        orderItem.getOrder().getOrderId(),
                        orderItem.getTicket().getTicketId(),
                        orderItem.getPrice(),
                        orderItem.getQuantity(),
                        orderItem.getTicket().isActive()
                ))
                .collect(Collectors.toList());
    }

    public String getQRCodeForOrderItem(int orderItemId) throws WriterException, IOException {
        OrderItem orderItem = orderItemRepository.findById(orderItemId)
                .orElseThrow(() -> new RuntimeException("OrderItem not found"));

        return QRCodeGenerator.generateQRCodeBase64(orderItem.getQRCode(), 200, 200);
    }
}
































