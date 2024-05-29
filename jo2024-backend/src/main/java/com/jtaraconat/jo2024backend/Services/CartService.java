package com.jtaraconat.jo2024backend.Services;

import com.jtaraconat.jo2024backend.DTO.CartItemDTO;
import com.jtaraconat.jo2024backend.Exceptions.CartNotFoundException;
import com.jtaraconat.jo2024backend.Exceptions.TicketNotFoundException;
import com.jtaraconat.jo2024backend.Exceptions.UserNotFoundException;
import com.jtaraconat.jo2024backend.Models.Cart;
import com.jtaraconat.jo2024backend.Models.CartItem;
import com.jtaraconat.jo2024backend.Models.Ticket;
import com.jtaraconat.jo2024backend.Models.User;
import com.jtaraconat.jo2024backend.Repositories.CartItemRepository;
import com.jtaraconat.jo2024backend.Repositories.CartRepository;
import com.jtaraconat.jo2024backend.Repositories.TicketRepository;
import com.jtaraconat.jo2024backend.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Ticket> getTicketsInCartByUserId(int userId) {
        Cart cart = cartRepository.findByUserUserId(userId);
        if (cart == null) {
            throw new CartNotFoundException(userId);
        }

        List<CartItem> cartItems = cartItemRepository.findByCartCartId(cart.getCartId());
        return cartItems.stream()
                .map(CartItem::getTicket)
                .collect(Collectors.toList());
    }

    public List<CartItemDTO> getCartItemsByUserID(int userId) {
        Cart cart = cartRepository.findByUserUserId(userId);
        if (cart == null) {
            throw new RuntimeException("Cart not found for customer ID: " + userId);
        }
        List<CartItem> cartItems = cartItemRepository.findByCartCartId(cart.getCartId());
        return cartItems.stream()
                .map(cartItem -> new CartItemDTO(
                        cartItem.getCartItemId(),
                        cartItem.getTicket().getTicketId(),
                        cartItem.getTicket().getEventName(),
                        cartItem.getTicket().getEventLocation(),
                        cartItem.getTicket().getTime(),
                        cartItem.getTicket().getDay(),
                        cartItem.getTicket().getSport(),
                        cartItem.getTicket().getCity(),
                        cartItem.getTicket().getTicketType(),
                        cartItem.getTicket().getPrice(),
                        cartItem.getQuantity(),
                        cartItem.getTicket().getImage()
                ))
                .collect(Collectors.toList());
    }


    @Transactional
    public void addTicketToCart(int userId, int ticketId, int quantity){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));


        Cart cart = cartRepository.findByUserUserId(userId);
        if(cart == null){
            //create cart if doesn't exist
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }


        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(()-> new TicketNotFoundException(ticketId));


        //check if cart already have this ticket
        CartItem existingCartItem = cartItemRepository.findByCartCartIdAndTicketTicketId(cart.getCartId(), ticketId);
        if (existingCartItem != null) {
            existingCartItem.setQuantity(quantity);
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setTicket(ticket);
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
        }
    }
}
