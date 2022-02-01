package com.prajina.academy.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.prajina.academy.model.CourseView;

public interface CourseService {

	ResponseEntity<CourseView[]> findAll(Integer pageNum, Integer pageSize, String sortBy);
	
	ResponseEntity<CourseView> findById(Long id);

	ResponseEntity<CourseView> findByName(String name);

	ResponseEntity<CourseView> findByDesc(String description);

	ResponseEntity<CourseView> save(CourseView course);

	void save(List<CourseView> course);
	
	ResponseEntity<CourseView> update(Long id, CourseView course);
	
	ResponseEntity<CourseView> patch(Long id, CourseView course);
	
	void deleteById(Long id);

	void deleteCourse(List<CourseView> courseList);
	
	ResponseEntity<Void> deleteWithExchange(Long id);

	boolean isCourseExist(CourseView course);

	

}
