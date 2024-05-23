package com.jtaraconat.jo2024backend.Controllers;

import com.jtaraconat.jo2024backend.DTO.CartItemDTO;
import com.jtaraconat.jo2024backend.Exceptions.CartItemNotFoundException;
import com.jtaraconat.jo2024backend.Models.Ticket;
import com.jtaraconat.jo2024backend.Repositories.CartItemRepository;
import com.jtaraconat.jo2024backend.Repositories.CartRepository;
import com.jtaraconat.jo2024backend.Services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @PostMapping("/api/cart/add")
    public ResponseEntity<Void> addTicketToCart(@RequestParam int userId, @RequestParam int ticketId, @RequestParam int quantity){
        try{
            cartService.addTicketToCart(userId, ticketId, quantity);
            return ResponseEntity.ok().build();
        } catch (Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/api/cart/tickets/{userId}")
    public ResponseEntity<List<Ticket>> getTicketsInCart(@PathVariable int userId) {
        List<Ticket> tickets =  cartService.getTicketsInCartByUserId(userId);
        if(tickets != null){
            return ResponseEntity.ok(tickets);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/cart/items/{userId}")
    public ResponseEntity<List<CartItemDTO>> getCartItems(@PathVariable int userId) {
        try {
            List<CartItemDTO> cartItems = cartService.getCartItemsByUserID(userId);
            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/cart/item/{cartItemId}")
    String deleteCartItem(@PathVariable Integer cartItemId){
        if(!cartItemRepository.existsById(cartItemId)) {
            throw new CartItemNotFoundException(cartItemId);
        }
        cartItemRepository.deleteById(cartItemId);
        return "Ticket item avec l'id: " + cartItemId + " a été supprimé";
    }



}


