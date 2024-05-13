package com.jtaraconat.jo2024backend.Controllers;

import com.jtaraconat.jo2024backend.Exceptions.TicketNotFoundException;
import com.jtaraconat.jo2024backend.Models.Ticket;
import com.jtaraconat.jo2024backend.Repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;


    @GetMapping("/api/tickets")
    List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("/api/ticket/{id}")
    Ticket getTicketByName(@PathVariable Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }
}