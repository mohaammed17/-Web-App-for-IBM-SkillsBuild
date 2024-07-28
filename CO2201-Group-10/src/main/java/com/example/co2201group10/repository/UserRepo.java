package com.example.co2201group10.repository;

import com.example.co2201group10.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
    public User findByUsername(String username);

    Object findAll(Sort progressionHandlerCurrentLevel);
}