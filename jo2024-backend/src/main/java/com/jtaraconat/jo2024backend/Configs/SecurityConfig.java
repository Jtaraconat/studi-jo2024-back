package com.jtaraconat.jo2024backend.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
/*
            "/api/ticket/*",
            "/api/ticket",
            "/api/cart/*",
            "/api/cart/items/*",
            "/api/cart/add",
            "/api/cart/tickets/*",
            "/api/order/*",
            "/api/user/*"
*/
    };


    @Bean // Indicates that this method returns a Spring bean.
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable() // Disable CSRF
                .authorizeRequests()
                .requestMatchers("/api/login").permitAll() // Allow access to /api/login without authentication
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build(); // Builds and returns the SecurityFilterChain.
    }


 }
