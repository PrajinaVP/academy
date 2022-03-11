package com.prajina.academy.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.prajina.academy.entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {
	
	List<CourseEntity> findByName(String name, Pageable pageable);
	List<CourseEntity> findByNameContaining(String name, Pageable pageable);
	
}
