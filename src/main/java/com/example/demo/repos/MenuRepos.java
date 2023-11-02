package com.example.demo.repos;

import com.example.demo.models.Menu;
import com.example.demo.models.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MenuRepos extends JpaRepository<Menu, Long> {
}
