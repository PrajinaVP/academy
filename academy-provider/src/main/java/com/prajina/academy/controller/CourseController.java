package com.prajina.academy.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prajina.academy.model.CourseView;
import com.prajina.academy.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService service;
	
	@GetMapping
	ResponseEntity<?> findAll(@RequestParam(value="pageNum", defaultValue="0") Integer pageNum,
			@RequestParam(value="pageSize", defaultValue="50") Integer pageSize,
			@RequestParam(value="sortBy", defaultValue="name") String sortBy) {
		System.out.println("Provider CTRL findAll  ");
		List<CourseView> courseViewList = 
				service.findAll(pageNum, pageSize, sortBy)
				.stream().map(CourseView :: convert)
				.collect(Collectors.toList());
		System.out.println("Provide SVC findAll courseViewList " + courseViewList);
		return new ResponseEntity<>(courseViewList, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> addCourse(@RequestBody CourseView course) {
		CourseView savedCourse = CourseView.convert(service.save(course));
		
		return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateCourse(@Valid @PathVariable(value="id", required=true) Long id,
			@RequestBody CourseView course) {
		CourseView savedCourse = CourseView.convert(service.update(id, course));
		
		return new ResponseEntity<>(savedCourse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@Valid @PathVariable(value="id", required=true) Long id) {
		service.deleteById(id);
	}
	
	@PutMapping("/{courseId}/module/add/{moduleId}")
	public ResponseEntity<?> addModule(@Valid @PathVariable(value="courseId", required=true) Long id,
			@PathVariable(value="moduleId", required=true) Long moduleId) {
		CourseView savedCourse =  CourseView.convert(service.addModule(id, moduleId));
		
		return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
	}

	@PutMapping("/{courseId}/module/remove/{moduleId}")
	public ResponseEntity<?> removeModule(@Valid @PathVariable(value="courseId", required=true) Long id,
			@PathVariable(value="moduleId", required=true) Long moduleId) {
		CourseView savedCourse =  CourseView.convert(service.removeModule(id, moduleId));
		
		return ResponseEntity.ok(savedCourse);
	}

}
