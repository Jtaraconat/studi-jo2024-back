package com.jtaraconat.jo2024backend.Services;

import com.jtaraconat.jo2024backend.Models.OrderItem;
import com.jtaraconat.jo2024backend.Models.Ticket;
import com.jtaraconat.jo2024backend.Repositories.OrderItemRepository;
import com.jtaraconat.jo2024backend.Repositories.TicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService{

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public void deleteTicket(int ticketId){
        List<OrderItem> orderItems = orderItemRepository.findByTicketTicketId(ticketId);
        if(!orderItems.isEmpty()){
            Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(()-> new RuntimeException("ticket not found"));
            ticket.setIsActive(false);
            ticketRepository.save(ticket);
        } else {
            ticketRepository.deleteById(ticketId);
        }
    }
}
