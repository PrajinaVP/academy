package com.prajina.academy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.prajina.academy.api.Course;
import com.prajina.academy.entity.CourseImpl;
import com.prajina.academy.repository.CourseRepository;
import com.prajina.academy.repository.ModuleRepository;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository repository;
	
	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	ObjectMapper objMapper;
	
	@Override
	public List<Course> findAll(Integer pageNum, Integer size, String sortBy) {
		System.out.println("Provide SVC findAll");
		Pageable page = PageRequest.of(pageNum, size, Sort.by(sortBy));
		Page<CourseImpl> pagedCourse = repository.findAll(page);
		
		System.out.println("Provide SVC findAll pagedCourse " + pagedCourse);
		
		List<CourseImpl> courseImplList = pagedCourse.getContent();
		System.out.println("Provide SVC courseImplList " + courseImplList);
		
		return ImmutableList.copyOf(pagedCourse.getContent());
	}
	
	@Override
	public Course save(Course course) {
		if (course == null) {
			throw new RuntimeException("No course provided to save!");
		}
		
		return repository.save(CourseImpl.convert(course));
	}
	
	@Override
	public Course update(Long id, Course course) {
		if (id == null) {
			throw new RuntimeException("No course id provided to update!");
		}
		if (course == null) {
			throw new RuntimeException("No course provided to update!");
		}
		
		Optional<CourseImpl> courseFromDB = Optional.ofNullable(repository.findById(id))
				.orElseThrow(() -> new RuntimeException("Course with id " + id + " not found!" ));
		
		CourseImpl courseToUpdate = courseFromDB.get();
		courseToUpdate.setName(course.getName());
		courseToUpdate.setDescription(course.getDescription());
		courseToUpdate.setContact(course.getContact());
		courseToUpdate.setVersion(course.getVersion());
		courseToUpdate.setStatus(course.getStatus());
		courseToUpdate.setModules(course.getModules());
		
		return repository.save(courseToUpdate);
	}
	
	@Override
	public void deleteById(Long id) {
		if (id == null) {
			throw new RuntimeException("No course id provided to delete!");
		}
		repository.deleteById(id);
	}
	
	@Override
	public Course addModule(Long courseId, Long moduleId) {
		if (courseId == null) {
			throw new RuntimeException("No course id provided to update!");
		}
		if (moduleId == null) {
			throw new RuntimeException("No module provided to update!");
		}
		Optional<CourseImpl> courseFromDB = Optional.ofNullable(repository.findById(courseId))
				.orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found!" ));

		Optional<com.prajina.academy.entity.ModuleImpl> moduleFromDB = Optional.ofNullable(moduleRepository.findById(moduleId))
				.orElseThrow(() -> new RuntimeException("Module with id " + moduleId + " not found!" ));
		
		CourseImpl courseToUpdate = courseFromDB.get();
		courseToUpdate.getModules().add(moduleFromDB.get());
		
		return repository.save(courseToUpdate);
	}
	
	@Override
	public Course removeModule(Long courseId, Long moduleId) {
		if (courseId == null) {
			throw new RuntimeException("No course id provided to update!");
		}
		if (moduleId == null) {
			throw new RuntimeException("No module id provided to update!");
		}
		
		Optional<CourseImpl> courseFromDB = Optional.ofNullable(repository.findById(courseId))
				.orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found!" ));

		
		CourseImpl courseToUpdate = courseFromDB.get();
		courseToUpdate.getModules().removeIf(module -> module.getId() == moduleId);
		
		return repository.save(courseToUpdate);
	}
}
