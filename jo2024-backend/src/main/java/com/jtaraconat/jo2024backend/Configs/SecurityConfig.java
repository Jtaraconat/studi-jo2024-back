package com.jtaraconat.jo2024backend.Configs;

import com.jtaraconat.jo2024backend.Models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
@CrossOrigin("http://localhost:3000")
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;


    private static final String[] WHITE_LIST = {
            "/",
            "/api/register",
            "/api/tickets",
            "/api/login",
            "/api/user/*",
    };


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable() // Disable CSRF
                .authorizeRequests()
                .requestMatchers(HttpMethod.DELETE,"/api/ticket/*").hasRole(Role.ADMIN.toString())
                .requestMatchers(HttpMethod.POST,"/api/ticket/*").hasRole(Role.ADMIN.toString())
                .requestMatchers(HttpMethod.PUT,"/api/ticket/*").hasRole(Role.ADMIN.toString())
                .requestMatchers(WHITE_LIST).permitAll() // Allow access to /api/login without authentication
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build(); // Builds and returns the SecurityFilterChain.
    }


 }
