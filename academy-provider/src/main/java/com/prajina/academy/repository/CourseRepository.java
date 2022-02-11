package com.prajina.academy.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prajina.academy.entity.CourseImpl;

public interface CourseRepository extends JpaRepository<CourseImpl, Long> {
	
	List<CourseImpl> findByName(String name, Pageable pageable);
	List<CourseImpl> findByNameContaining(String name, Pageable pageable);
	
}
