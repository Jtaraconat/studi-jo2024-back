package com.jtaraconat.jo2024backend.Repositories;

import com.jtaraconat.jo2024backend.Models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByCartCartId(int cartId);
    CartItem findByCartCartIdAndTicketTicketId(int cartId, int ticketID);
}
