package com.prajina.academy.service;

import java.util.List;

import com.prajina.academy.api.Course;


public interface CourseService {
	
	 List<Course> findAll(Integer pageNum, Integer size, String sortBy);
	
	 Course save(Course course);
	
	 Course update(Long id, Course course);
	 void deleteById(Long id);
	
	 Course addModule(Long courseId, Long moduleId);
	 Course removeModule(Long courseId, Long moduleId);
}
