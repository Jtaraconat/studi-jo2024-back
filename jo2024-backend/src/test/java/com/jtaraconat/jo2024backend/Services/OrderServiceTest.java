package com.jtaraconat.jo2024backend.Services;

import com.google.zxing.WriterException;
import com.jtaraconat.jo2024backend.Models.*;
import com.jtaraconat.jo2024backend.Repositories.CartItemRepository;
import com.jtaraconat.jo2024backend.Repositories.CartRepository;
import com.jtaraconat.jo2024backend.Repositories.OrderItemRepository;
import com.jtaraconat.jo2024backend.Repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderService orderService;

    private Cart cart;
    private List<CartItem> cartItems;
    private User user;
    private Order order;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        user = new User();
        user.setUserId(15);
        user.setUniqueUserKey(UUID.randomUUID());

        cart = new Cart();
        cart.setCartId(15);
        cart.setUser(user);



        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(15);
        cartItem.setCart(cart);
        cartItem.setQuantity(1);
        Ticket ticket = new Ticket();
        ticket.setTicketId(15);
        ticket.setPrice(100);
        cartItem.setTicket(ticket);

        cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        order = new Order();
        order.setUser(user);
        order.setOrderId(2);

    }

    @Test
    public void testCreateOrderFromCart() throws SQLException, IOException, WriterException {
        when(cartRepository.findByUserUserId(user.getUserId())).thenReturn(cart);
        when(cartItemRepository.findByCartCartId(cart.getCartId())).thenReturn(cartItems);
        assertEquals(user, order.getUser());

    }

    @Test
    public void testCreateOrderFromEmptyCart() {
        when(cartRepository.findByUserUserId(user.getUserId())).thenReturn(cart);
        when(cartItemRepository.findByCartCartId(cart.getCartId())).thenReturn(new ArrayList<>());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderService.createOrderFromCart(user.getUserId());
        });

        assertEquals("cart is empty", exception.getMessage());
    }
}
