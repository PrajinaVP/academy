package com.prajina.academy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prajina.academy.dao.CourseDAO;
import com.prajina.academy.model.Course;
import com.prajina.academy.transformer.CourseMapper;

@Service("CourseService")
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDAO dao;
	
	@Autowired
	private CourseMapper mapper;

	@Override
	@Transactional
	public List<Course> findAll() {
		return mapper.toModel(dao.findAll());
	}

	@Override
	@Transactional
	public Course findById(Long id) {
		return mapper.toModel(dao.findById(id));
	}

	@Override
	@Transactional
	public Course findByName(String name) {
		return mapper.toModel(dao.findByName(name));
	}

	@Override
	@Transactional
	public List<Course> findByDesc(String desc) {
		return mapper.toModel(dao.findByDesc(desc));
	}

	@Override
	@Transactional
	public void save(Course course) {
		dao.save(mapper.toEntity(course));
	}

	@Override
	@Transactional
	public void save(List<Course> courseList) {
		if (courseList == null) {
			throw new RuntimeException("No course provided!");
		}

		for (Course course : courseList) {
			save(course);
		}
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		dao.delete(id);
	}
	
	@Override
	@Transactional
	public void deleteCourse(List<Course> courseList) {
		if (courseList == null) {
			throw new RuntimeException("No course provided!");
		}

		for (Course course : courseList) {
			deleteById(course.getId());
		}
	}

	@Override
	@Transactional
	public boolean isCourseExist(Course course) {

		return findById(course.getId()) != null;
	}
}
