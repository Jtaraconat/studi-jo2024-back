package com.jtaraconat.jo2024backend.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jtaraconat.jo2024backend.Models.Ticket;
import com.jtaraconat.jo2024backend.Repositories.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
            private TicketRepository ticketRepository;

    Ticket ticket = new Ticket(12, "France vs brésil", "Stade de france", "15h00", "25/07", 150, "url image", "Football", "Paris", "Solo", true, null);
    Ticket newTicket = new Ticket(12, "France vs brésil", "Stade de france", "16h00", "16/08", 150, "url image", "Football", "Paris", "Duo", true, null);

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAuthorizedAccessToAdminPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testUnauthorizedAccessToUserPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testUnauthorizedAccessToUserEndpointDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/ticket/602")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testUnauthorizedAccessToAdminEndpointDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/ticket/2102")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testAuthorizedAccessToAdminPut() throws Exception{
        doReturn(Optional.of(ticket)).when(ticketRepository).findById(602);
        doReturn(newTicket).when(ticketRepository).save(any(Ticket.class));
        mockMvc.perform(put("/api/ticket/602")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testUnauthorizedAccessToUserPut() throws Exception{
        doReturn(Optional.of(ticket)).when(ticketRepository).findById(602);
        doReturn(newTicket).when(ticketRepository).save(any(Ticket.class));

        mockMvc.perform(put("/api/ticket/602")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isForbidden());
    }

}
