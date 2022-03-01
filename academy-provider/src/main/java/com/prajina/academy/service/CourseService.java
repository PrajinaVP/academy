package com.prajina.academy.service;

import java.util.List;

import com.prajina.academy.model.Course;


public interface CourseService {
	
	public List<Course> findAll(Integer pageNum, Integer size, String sortBy);
	
	public Course save(Course course);
	
	public Course update(Long id, Course course);
	public void deleteById(Long id);
	
	public Course addModule(Long courseId, Long moduleId);
	public Course removeModule(Long courseId, Long moduleId);
}
