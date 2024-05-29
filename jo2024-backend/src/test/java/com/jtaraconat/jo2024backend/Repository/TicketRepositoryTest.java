package com.jtaraconat.jo2024backend.Repository;

import com.jtaraconat.jo2024backend.Models.Ticket;
import com.jtaraconat.jo2024backend.Repositories.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;

    @Test
    public void testSaveTicket() {
        Ticket ticket = new Ticket(10, "100m", "stade de france", "20h00", "24/07", 150, "url image", "course", "paris","solo", true,null );
        Ticket savedTicket = ticketRepository.save(ticket);

        assertThat(savedTicket.getTicketId()).isNotNull();
        assertThat(savedTicket.getPrice()).isEqualTo(150);
        assertThat(savedTicket.getTicketType()).isEqualTo("solo");
    }

    @Test
    public void testFindTicketById() {
        Ticket ticket = new Ticket(10, "100m", "stade de france", "20h00", "24/07", 150, "url image", "course", "paris","solo", true,null );
        Ticket savedTicket = ticketRepository.save(ticket);

        Optional<Ticket> foundTicket = ticketRepository.findById(savedTicket.getTicketId());

        assertThat(foundTicket).isPresent();
        assertThat(foundTicket.get().getCity()).isEqualTo("paris");
    }

    public void testFindAllTickets() {
        Ticket ticket1 = new Ticket(10, "100m", "stade de france", "20h00", "24/07", 150, "url image", "course", "paris","solo", true,null );
        Ticket ticket2 = new Ticket(15, "400m", "stade de france", "20h00", "24/07", 150, "url image", "course", "paris","solo", true,null );
        ticketRepository.save(ticket1);
        ticketRepository.save(ticket2);

        List<Ticket> tickets = ticketRepository.findAll();

        assertThat(tickets).hasSize(2);
        assertThat(tickets).extracting(Ticket::getEventName).containsExactlyInAnyOrder("100m", "400m");
    }

    @Test
    public void testDeleteTicketById() {
        Ticket ticket = new Ticket(10, "100m", "stade de france", "20h00", "24/07", 150, "url image", "course", "paris","solo", true,null );
        Ticket savedTicket = ticketRepository.save(ticket);

        ticketRepository.deleteById(savedTicket.getTicketId());

        Optional<Ticket> foundTicket = ticketRepository.findById(savedTicket.getTicketId());

        assertThat(foundTicket).isNotPresent();
    }


}
