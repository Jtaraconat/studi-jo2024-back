package com.jtaraconat.jo2024backend.Repositories;

import com.jtaraconat.jo2024backend.Models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    List<OrderItem> findByTicketTicketId(int ticketId);
}
