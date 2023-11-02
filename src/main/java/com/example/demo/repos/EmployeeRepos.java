package com.example.demo.repos;

import com.example.demo.models.Employee;
import com.example.demo.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepos extends JpaRepository<Employee, Long> {
}
