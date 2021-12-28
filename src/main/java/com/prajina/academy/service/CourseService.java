package com.prajina.academy.service;

import java.util.List;

import com.prajina.academy.model.Course;

public interface CourseService {

	List<Course> findAll();

	Course findById(Integer id);

	Course findByName(String name);

	List<Course> findByDesc(String desc);

	Course create(Course course);

	List<Course> create(List<Course> course);

	Course update(Course course);

	void updateCourse(List<Course> course);

	void deleteCourse(List<Course> courseList);

	void deleteById(Integer id);

	void deleteAll();

	boolean isCourseExist(Course course);

}
