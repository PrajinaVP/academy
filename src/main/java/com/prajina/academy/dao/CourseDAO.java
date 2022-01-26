package com.prajina.academy.dao;

import java.util.List;

import com.prajina.academy.entity.Course;

public interface CourseDAO {
	
	public List<Course> findAll();
	
	public Course findById(Long id);
	
	public Course findByName(String name);
	
	public List<Course> findByDesc(String desc);
	
	public Course getCourse(Course course);
	
	public void save(Course course);
	
	public void delete(Long courseId);

}
