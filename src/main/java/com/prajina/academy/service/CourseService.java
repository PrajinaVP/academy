package com.prajina.academy.service;

import java.util.List;

import com.prajina.academy.model.Course;

public interface CourseService {

	List<Course> findAll();

	Course findById(long id);

	Course findByName(String name);

	List<Course> findByDesc(String desc);

	Course create(Course course);

	List<Course> create(List<Course> course);

	Course update(Course course);

	void updateCourse(List<Course> course);

	void deleteCourse(List<Course> courseList);

	void deleteAll();

	void deleteById(long id);

	boolean isCourseExist(Course course);

}
