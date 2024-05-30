package com.jtaraconat.jo2024backend.Services;

import com.jtaraconat.jo2024backend.Controllers.AuthenticationRequest;
import com.jtaraconat.jo2024backend.Controllers.AuthenticationResponse;
import com.jtaraconat.jo2024backend.Controllers.RegisterRequest;
import com.jtaraconat.jo2024backend.Models.CustomUserDetails;
import com.jtaraconat.jo2024backend.Models.Role;
import com.jtaraconat.jo2024backend.Models.User;
import com.jtaraconat.jo2024backend.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    public AuthenticationResponse register(RegisterRequest request) {

            var user = User.builder()
                    .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(Role.USER)
                    .uniqueUserKey(request.getUniqueUserKey())
                    .build();
            repository.save(user);

        var  userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
        var jwtToken = jwtService.generateToken(userDetails);
        return createAuthenticationResponse(userDetails, jwtToken);
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user  = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var userDetails = customUserDetailsService.loadUserByUsername(request.getEmail());
        System.out.println(userDetails);

        var jwtToken = jwtService.generateToken(userDetails);
        return createAuthenticationResponse(userDetails, jwtToken);
    }

    private AuthenticationResponse createAuthenticationResponse(UserDetails userDetails, String jwtToken) {
        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(customUserDetails.getUserId())
                .firstname(customUserDetails.getFirstname())
                .lastname(customUserDetails.getLastname())
                .role(customUserDetails.getRole())
                .build();
    }
}
