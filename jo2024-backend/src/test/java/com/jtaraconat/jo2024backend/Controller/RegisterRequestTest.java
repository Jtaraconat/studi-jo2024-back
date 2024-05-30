package com.jtaraconat.jo2024backend.Controller;

import com.jtaraconat.jo2024backend.Controllers.RegisterRequest;
import com.jtaraconat.jo2024backend.Models.Role;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterRequestTest {

    @Test
    public void testGettersAndSetters() {
        RegisterRequest request = RegisterRequest.builder()
                .firstname("John")
                .lastname("Doe")
                .email("john@example.com")
                .password("password")
                .role(Role.USER)
                .uniqueUserKey(UUID.randomUUID())
                .build();


        assertEquals("John", request.getFirstname());
        assertEquals("Doe", request.getLastname());
        assertEquals("john@example.com", request.getEmail());
        assertEquals("password", request.getPassword());
        assertEquals(Role.USER, request.getRole());
        assertEquals(request.getUniqueUserKey(), request.getUniqueUserKey());


        request.setFirstname("Jane");
        request.setLastname("Smith");
        request.setEmail("jane@example.com");
        request.setPassword("newpassword");
        request.setRole(Role.ADMIN);
        UUID newKey = UUID.randomUUID();
        request.setUniqueUserKey(newKey);


        assertEquals("Jane", request.getFirstname());
        assertEquals("Smith", request.getLastname());
        assertEquals("jane@example.com", request.getEmail());
        assertEquals("newpassword", request.getPassword());
        assertEquals(Role.ADMIN, request.getRole());
        assertEquals(newKey, request.getUniqueUserKey());
    }
}
