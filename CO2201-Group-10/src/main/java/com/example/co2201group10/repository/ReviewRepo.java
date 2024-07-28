package com.example.co2201group10.repository;

import com.example.co2201group10.model.Review;
import com.example.co2201group10.model.User;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepo extends CrudRepository<Review, Long> {
}
