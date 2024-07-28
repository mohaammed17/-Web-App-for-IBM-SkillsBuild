package com.example.co2201group10.repository;

import com.example.co2201group10.model.Badge;
import org.springframework.data.repository.CrudRepository;

public interface BadgeRepo extends CrudRepository<Badge, Long> {
    public Badge findByName(String name);
}
