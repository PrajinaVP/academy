package com.prajina.academy.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prajina.academy.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
	
	List<Course> findByName(String name, Pageable pageable);
	List<Course> findByNameContaining(String name, Pageable pageable);
	
}
