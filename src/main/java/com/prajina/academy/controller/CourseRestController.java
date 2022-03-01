package com.prajina.academy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.prajina.academy.model.Course;
import com.prajina.academy.service.CourseService;

@RestController
@RequestMapping("/rest/course")
public class CourseRestController {
	
	Logger logger = LoggerFactory.getLogger(CourseRestController.class);

	@Autowired
	private CourseService courseService;

	@GetMapping(value = "")
	public ResponseEntity<?> listAllCourses() {
		logger.debug("Fetching all courses...");
		List<Course> courses = courseService.findAll();

		if (courses.isEmpty()) {
			return new ResponseEntity<>("No Courses Found", HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> getCourse(@PathVariable("id") Long id) {
		logger.debug("Fetching Course with id " + id);
		Course course = courseService.findById(id);
		if (course == null) {
			logger.debug("Course with id " + id + " not found");
			return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Void> createCourse(@RequestBody Course course, UriComponentsBuilder ucBuilder) {
		logger.debug("Creating Course " + course.getName());

		if (courseService.isCourseExist(course)) {
			logger.debug("A Course with name " + course.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		courseService.save(course);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/course/{id}").buildAndExpand(course.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCourse(@PathVariable("id") Long id, @RequestBody Course course) {
		logger.debug("Updating Course " + id);

		Course currentCourse = courseService.findById(id);

		if (currentCourse == null) {
			logger.debug("Course with id " + id + " not found");
			
			return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
		}
		
		courseService.save(course);
		Course updatedCourse = courseService.findById(id);
		
		return new ResponseEntity<Course>(updatedCourse, HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Course> updatePartialCourse(@PathVariable("id") Long id, @RequestBody Course course) {
		logger.debug("Partial pdating Course " + id);

		Course currentCourse = courseService.findById(id);

		if (currentCourse == null) {
			logger.debug("Course with id " + id + " not found");
		
			return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
		}
		
		courseService.save(course);
		Course updatedCourse = courseService.findById(id);
		
		return new ResponseEntity<Course>(updatedCourse, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Course> deleteCourseById(@PathVariable("id") Long id) {
		logger.debug("Fetching & Deleting Course with id " + id);

		Course course = courseService.findById(id);
		if (course == null) {
			logger.debug("Unable to delete. Course with id " + id + " not found");
			return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
		}

		courseService.deleteById(id);
		return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
	}
}
