package com.jtaraconat.jo2024backend.Controllers;

import com.jtaraconat.jo2024backend.Models.User;
import com.jtaraconat.jo2024backend.Services.AuthenticationService;
import com.jtaraconat.jo2024backend.Services.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"https://studi-jo2024.web.app"})
@Tag(name = "Authentication API", description = "API for managing authentication")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService, AuthenticationService authenticationService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/api/login")
    @Operation(method = "POST", summary = "Login a user", description = "Allows a user to login")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthenticationResponse("Invalid username or password", 0, null, null, null));
        }

        User user = (User) userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        String token = jwtService.generateToken(user);

        AuthenticationResponse authResponse = new AuthenticationResponse(
                token,
                user.getUserId(),
                user.getFirstname(),
                user.getLastname(),
                user.getRole()
        );

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/api/register")
    @Operation(method = "POST", summary = "Register a new user", description = "Allows a user to register an account")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        AuthenticationResponse response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

}



