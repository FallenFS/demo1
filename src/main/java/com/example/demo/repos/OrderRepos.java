package com.example.demo.repos;

import com.example.demo.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepos extends JpaRepository<Orders, Long> {

}
