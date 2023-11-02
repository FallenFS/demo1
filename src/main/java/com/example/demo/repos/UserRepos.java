package com.example.demo.repos;

import com.example.demo.models.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepos extends JpaRepository<UserClass, Long> {
    Optional<UserClass> deleteById(long id);
}
