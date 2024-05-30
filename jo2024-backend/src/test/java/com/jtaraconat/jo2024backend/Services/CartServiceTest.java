package com.jtaraconat.jo2024backend.Services;

import com.jtaraconat.jo2024backend.Exceptions.CartNotFoundException;
import com.jtaraconat.jo2024backend.Models.Cart;
import com.jtaraconat.jo2024backend.Models.CartItem;
import com.jtaraconat.jo2024backend.Models.Ticket;
import com.jtaraconat.jo2024backend.Models.User;
import com.jtaraconat.jo2024backend.Repositories.CartItemRepository;
import com.jtaraconat.jo2024backend.Repositories.CartRepository;
import com.jtaraconat.jo2024backend.Repositories.TicketRepository;
import com.jtaraconat.jo2024backend.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testGetTicketsInCartByUserId_CartNotFound() {

        int userId = 1;
        when(cartRepository.findByUserUserId(userId)).thenReturn(null);


        assertThrows(CartNotFoundException.class, () -> cartService.getTicketsInCartByUserId(userId));
    }


    @Test
    public void testAddTicketToCart() {

        int userId = 1;
        int ticketId = 1;
        int quantity = 2;
        User user = new User();
        user.setUserId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Cart cart = new Cart();
        cart.setCartId(1);
        when(cartRepository.findByUserUserId(userId)).thenReturn(cart);

        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketId);
        when(ticketRepository.findById(ticketId)).thenReturn(Optional.of(ticket));

        when(cartItemRepository.findByCartCartIdAndTicketTicketId(cart.getCartId(), ticketId)).thenReturn(null);


        cartService.addTicketToCart(userId, ticketId, quantity);


        verify(cartItemRepository, times(1)).save(any(CartItem.class));
    }
}
