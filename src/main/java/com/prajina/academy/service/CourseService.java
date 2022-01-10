package com.prajina.academy.service;

import java.util.List;

import com.prajina.academy.model.Course;

public interface CourseService {

	List<Course> findAll();

	Course findById(Long id);

	Course findByName(String name);

	List<Course> findByDesc(String desc);

	void save(Course course);

	void save(List<Course> course);

	void deleteCourse(List<Course> courseList);

	void deleteById(Long id);

	boolean isCourseExist(Course course);

}
