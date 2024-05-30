package com.jtaraconat.jo2024backend.Controllers;

import com.jtaraconat.jo2024backend.DTO.CartItemDTO;
import com.jtaraconat.jo2024backend.Exceptions.CartItemNotFoundException;
import com.jtaraconat.jo2024backend.Models.Ticket;
import com.jtaraconat.jo2024backend.Repositories.CartItemRepository;
import com.jtaraconat.jo2024backend.Repositories.CartRepository;
import com.jtaraconat.jo2024backend.Services.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://studi-jo2024.web.app"})
@Tag(name ="Cart API", description = "API for managing shopping cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemRepository cartItemRepository;

    @PostMapping("/api/cart/add")
    @Operation(method = "POST", summary = "Add a ticket to a cart", description = "Allows a user to a ticket to his cart found by ID")
    public ResponseEntity<Void> addTicketToCart(@RequestParam int userId, @RequestParam int ticketId, @RequestParam int quantity){
        try{
            cartService.addTicketToCart(userId, ticketId, quantity);
            return ResponseEntity.ok().build();
        } catch (Exception e ) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/api/cart/tickets/{userId}")
    @Operation(method = "GET", summary = "Get all tickets in a cart", description = "Allows a user to get all tickets in his cart")
    public ResponseEntity<List<Ticket>> getTicketsInCart(@PathVariable int userId) {
        List<Ticket> tickets =  cartService.getTicketsInCartByUserId(userId);
        if(tickets != null){
            return ResponseEntity.ok(tickets);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/api/cart/items/{userId}")
    @Operation(method = "GET", summary = "Get all cart items", description = "Allows the frontend to get all cartItemDTO in a user cart")
    public ResponseEntity<List<CartItemDTO>> getCartItems(@PathVariable int userId) {
        try {
            List<CartItemDTO> cartItems = cartService.getCartItemsByUserID(userId);
            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/cart/item/{cartItemId}")
    @Operation(method = "DELETE", summary = "Delete a cart item", description = "Allows a user to delete a ticket in his cart")
    String deleteCartItem(@PathVariable Integer cartItemId){
        if(!cartItemRepository.existsById(cartItemId)) {
            throw new CartItemNotFoundException(cartItemId);
        }
        cartItemRepository.deleteById(cartItemId);
        return "Ticket item avec l'id: " + cartItemId + " a été supprimé";
    }

}


