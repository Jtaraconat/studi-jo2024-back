package com.jtaraconat.jo2024backend.Repositories;

import com.jtaraconat.jo2024backend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
