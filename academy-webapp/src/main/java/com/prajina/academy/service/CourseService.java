package com.prajina.academy.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.prajina.academy.model.Course;

public interface CourseService {

	ResponseEntity<Course[]> findAll(Integer pageNum, Integer pageSize, String sortBy);
	
	ResponseEntity<Course> findById(Long id);

	ResponseEntity<Course> findByName(String name);

	ResponseEntity<Course> findByDesc(String description);

	ResponseEntity<Course> save(Course course);

	void save(List<Course> course);
	
	ResponseEntity<Course> update(Long id, Course course);
	
	ResponseEntity<Course> patch(Long id, Course course);
	
	void deleteById(Long id);

	void deleteCourse(List<Course> courseList);
	
	ResponseEntity<Void> deleteWithExchange(Long id);

	boolean isCourseExist(Course course);

	

}
