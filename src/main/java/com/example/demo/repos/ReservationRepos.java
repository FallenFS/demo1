package com.example.demo.repos;

import com.example.demo.models.Orders;
import com.example.demo.models.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepos extends JpaRepository<Reservations, Long> {
}
