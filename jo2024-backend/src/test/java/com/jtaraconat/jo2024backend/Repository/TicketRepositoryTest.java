package com.jtaraconat.jo2024backend.Repository;

import com.jtaraconat.jo2024backend.Models.Ticket;
import com.jtaraconat.jo2024backend.Repositories.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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


    @Test
    public void testDeleteTicketById() {
        Ticket ticket = new Ticket(10, "100m", "stade de france", "20h00", "24/07", 150, "url image", "course", "paris","solo", true,null );
        Ticket savedTicket = ticketRepository.save(ticket);

        ticketRepository.deleteById(savedTicket.getTicketId());

        Optional<Ticket> foundTicket = ticketRepository.findById(savedTicket.getTicketId());

        assertThat(foundTicket).isNotPresent();
    }


}
