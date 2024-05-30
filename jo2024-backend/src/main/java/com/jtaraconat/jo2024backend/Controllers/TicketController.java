package com.jtaraconat.jo2024backend.Controllers;

import com.jtaraconat.jo2024backend.Exceptions.TicketNotFoundException;
import com.jtaraconat.jo2024backend.Models.Ticket;
import com.jtaraconat.jo2024backend.Repositories.TicketRepository;
import com.jtaraconat.jo2024backend.Services.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"https://studi-jo2024.web.app"})
@Tag(name ="Ticket API", description = "API for managing tickets")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("api/ticket")
    @Operation(method = "GET", summary = "Add a ticket", description = "Allows an admin to add a new ticket")
    Ticket newTicket(@RequestBody Ticket newTicket){
        return ticketRepository.save(newTicket);
    }


    @GetMapping("/api/tickets")
    @Operation(method = "GET", summary = "Get all tickets", description = "Allows the frontend to get all the tickets")
    List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("/api/ticket/{id}")
    @Operation(method = "GET", summary = "Get a ticket by ID", description = "Allows the frontend to get a ticket based on his ID")
    Ticket getTicketByName(@PathVariable Integer id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/api/ticket/{ticketId}")
    @Operation(method = "PUT", summary = "Update a ticket", description = "Allows an admin to update a ticket found by ticketID")
    Ticket updateTicket(@RequestBody Ticket newTicket, @PathVariable Integer ticketId) {
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

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/api/ticket/{ticketId}")
    @Operation(method = "DELETE", summary = "Delete a ticket", description = "Allows an admin to delete a ticket found by ticketID")
    public ResponseEntity<String> deleteTicket(@PathVariable int ticketId) {
        try {
            ticketService.deleteTicket(ticketId);
            return ResponseEntity.ok("Ticket deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}