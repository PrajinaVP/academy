package com.prajina.academy.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.prajina.academy.entity.CourseEntity;
import com.prajina.academy.model.Course;


public interface CourseService {
	
	public List<Course> findAll(Integer pageNum, Integer size, String sortBy);
	
	public Course create(Course course);
	
	public Course update(Long id, Course course);
	public void deleteById(Long id);
	
	public Course addModule(Long courseId, Long moduleId);
	public Course removeModule(Long courseId, Long moduleId);
}
