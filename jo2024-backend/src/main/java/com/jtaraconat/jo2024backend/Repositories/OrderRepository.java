package com.jtaraconat.jo2024backend.Repositories;

import com.jtaraconat.jo2024backend.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
