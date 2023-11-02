package com.example.demo.repos;

import com.example.demo.models.Client;
import com.example.demo.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepos extends JpaRepository<Client, Long> {
}
