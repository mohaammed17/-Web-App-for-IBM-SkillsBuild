package com.example.co2201group10.repository;

import com.example.co2201group10.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepo extends CrudRepository<Course, Long> {
}