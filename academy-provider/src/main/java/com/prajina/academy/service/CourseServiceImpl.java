package com.prajina.academy.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prajina.academy.model.Module;
import com.prajina.academy.model.Course;
import com.prajina.academy.repository.CourseRepository;
import com.prajina.academy.repository.ModuleRepository;
import com.prajina.academy.transformer.CourseMapper;
import com.prajina.academy.transformer.ModuleMapper;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository repository;
	
	@Autowired
	private ModuleRepository moduleRepository;
	
	@Autowired
	private CourseMapper mapper;
	
	@Autowired
	private ModuleMapper moduleMapper;
	
	@Autowired
	ObjectMapper objMapper;
	
	@Override
	public List<Course> findAll(Integer pageNum, Integer size, String sortBy) {
		
		Pageable page = PageRequest.of(pageNum, size, Sort.by(sortBy));
		Page<com.prajina.academy.entity.Course> pagedCourse = repository.findAll(page);
		
		return mapper.toModel(pagedCourse.getContent());
	}
	
	@Override
	public Course save(Course course) {
		if (course == null) {
			throw new RuntimeException("No course provided to save!");
		}
		
		return mapper.toModel(repository.save(mapper.toEntity(course)));
	}
	
	@Override
	public Course update(Long id, Course course) {
		if (id == null) {
			throw new RuntimeException("No course id provided to update!");
		}
		if (course == null) {
			throw new RuntimeException("No course provided to update!");
		}
		
		System.out.println("PROVIDER SVC UPDATE id :: " + id + "\n course :: " + course);
		//course.setId(id);
		
		Optional<com.prajina.academy.entity.Course> courseFromDB = Optional.ofNullable(repository.findById(id))
				.orElseThrow(() -> new RuntimeException("Course with id " + id + " not found!" ));
		System.out.println("PROVIDER SVC UPDATE courseFromDB :: " + courseFromDB);
		com.prajina.academy.entity.Course courseToUpdate = courseFromDB.get();
		courseToUpdate.setName(course.getName());
		courseToUpdate.setDescription(course.getDescription());
		courseToUpdate.setContact(course.getContact());
		courseToUpdate.setVersion(course.getVersion());
		courseToUpdate.setStatus(course.getStatus());
		courseToUpdate.setModules(moduleMapper.toEntity(course.getModules()));
		System.out.println("PROVIDER SVC UPDATE courseToUpdate :: " + courseToUpdate);
		return mapper.toModel(repository.save(courseToUpdate));
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
		Optional<com.prajina.academy.entity.Course> courseFromDB = Optional.ofNullable(repository.findById(courseId))
				.orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found!" ));

		Optional<com.prajina.academy.entity.Module> moduleFromDB = Optional.ofNullable(moduleRepository.findById(moduleId))
				.orElseThrow(() -> new RuntimeException("Module with id " + moduleId + " not found!" ));
		
		com.prajina.academy.entity.Course courseToUpdate = courseFromDB.get();
		courseToUpdate.getModules().add(moduleFromDB.get());
		
		return mapper.toModel(repository.save(courseToUpdate));
	}
	
	@Override
	public Course removeModule(Long courseId, Long moduleId) {
		if (courseId == null) {
			throw new RuntimeException("No course id provided to update!");
		}
		if (moduleId == null) {
			throw new RuntimeException("No module id provided to update!");
		}
		
		Optional<com.prajina.academy.entity.Course> courseFromDB = Optional.ofNullable(repository.findById(courseId))
				.orElseThrow(() -> new RuntimeException("Course with id " + courseId + " not found!" ));

		
		com.prajina.academy.entity.Course courseToUpdate = courseFromDB.get();
		courseToUpdate.getModules().removeIf(module -> module.getId() == moduleId);
		
		return mapper.toModel(repository.save(courseToUpdate));
	}
}
