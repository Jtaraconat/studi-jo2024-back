package com.jtaraconat.jo2024backend.Controllers;

import com.jtaraconat.jo2024backend.Exceptions.TicketNotFoundException;
import com.jtaraconat.jo2024backend.Models.Ticket;
import com.jtaraconat.jo2024backend.Repositories.TicketRepository;
import com.jtaraconat.jo2024backend.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;

    @PostMapping("api/ticket")
    Ticket newTicket(@RequestBody Ticket newTicket){
        return ticketRepository.save(newTicket);
    }


    @GetMapping("/api/tickets")
    List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("/api/ticket/{id}")
    Ticket getTicketByName(@PathVariable Integer id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    @PutMapping("/api/ticket/{ticketId}")
    Ticket updateProduct(@RequestBody Ticket newTicket, @PathVariable Integer ticketId) {
        return ticketRepository.findById(ticketId)
                .map(ticket -> {
                    ticket.setEventName(newTicket.getEventName());
                    ticket.setEventLocation(newTicket.getEventLocation());
                    ticket.setTime(newTicket.getTime());
                    ticket.setDay(newTicket.getDay());
                    ticket.setPrice(newTicket.getPrice());
                    ticket.setImage(newTicket.getImage());
                    ticket.setSport(newTicket.getSport());
                    ticket.setCity(newTicket.getCity());
                    ticket.setTicketType(newTicket.getTicketType());
                    return ticketRepository.save(ticket);
                })
                .orElseThrow(()->new TicketNotFoundException(ticketId));
    }

    @DeleteMapping("/api/ticket/{ticketId}")
    public ResponseEntity<String> deleteTicket(@PathVariable int ticketId) {
        try {
            ticketService.deleteTicket(ticketId);
            return ResponseEntity.ok("Ticket deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}