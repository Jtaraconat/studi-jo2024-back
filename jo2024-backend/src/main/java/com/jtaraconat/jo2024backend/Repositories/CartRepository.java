package com.jtaraconat.jo2024backend.Repositories;

import com.jtaraconat.jo2024backend.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserUserId(int userId);
}
