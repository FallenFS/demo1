package com.example.demo.repos;

import com.example.demo.models.OrderedDishes;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderedDishesRepos extends JpaRepository<OrderedDishes, Long> {
}
