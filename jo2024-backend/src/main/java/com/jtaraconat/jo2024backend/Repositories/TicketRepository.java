package com.jtaraconat.jo2024backend.Repositories;

import com.jtaraconat.jo2024backend.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {}
