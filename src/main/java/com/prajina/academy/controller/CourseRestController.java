package com.prajina.academy.controller;

import java.util.List;

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

	@Autowired
	private CourseService courseService;

	@GetMapping(value = "")
	public ResponseEntity<?> listAllCourses() {
		System.out.println("Rest GET");
		List<Course> courses = courseService.findAll();

		if (courses.isEmpty()) {
			return new ResponseEntity<>("No Courses Found", HttpStatus.OK);
		}

		System.out.println("courses not empty :: " + courses);
		System.out.println("response entity :: " + new ResponseEntity<List<Course>>(courses, HttpStatus.OK));
		return new ResponseEntity<>(courses, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Course> getCourse(@PathVariable("id") Long id) {
		System.out.println("Fetching Course with id " + id);
		Course course = courseService.findById(id);
		if (course == null) {
			System.out.println("Course with id " + id + " not found");
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Course>(course, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Void> createCourse(@RequestBody Course course, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Course " + course.getName());

		if (courseService.isCourseExist(course)) {
			System.out.println("A Course with name " + course.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		courseService.create(course);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/course/{id}").buildAndExpand(course.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Course> updateCourse(@PathVariable("id") Long id, @RequestBody Course course) {
		System.out.println("Updating Course " + id);

		Course currentCourse = courseService.findById(id);

		if (currentCourse == null) {
			System.out.println("Course with id " + id + " not found");
			//TODO Replace with 200s OK or 500
			return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
		}

		// TODO Delete commented lines after confirming that PATCH works
		/*
		 * currentCourse.setName(course.getName());
		 * currentCourse.setDesc(course.getDesc());
		 * currentCourse.setStatus(course.getStatus());
		 * currentCourse.setContact(course.getContact());
		 * 
		 * courseService.updateCourse(currentCourse);
		 */
		Course updatedCourse = courseService.update(course);
		return new ResponseEntity<Course>(updatedCourse, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Course> deleteCourseById(@PathVariable("id") Long id) {
		System.out.println("Fetching & Deleting Course with id " + id);

		Course course = courseService.findById(id);
		if (course == null) {
			System.out.println("Unable to delete. Course with id " + id + " not found");
			return new ResponseEntity<Course>(HttpStatus.NOT_FOUND);
		}

		courseService.deleteById(id);
		return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<Course> deleteAllCourses() {
		System.out.println("Deleting All Courses");

		courseService.deleteAll();
		List<Course> courses = courseService.findAll();
		if (courses == null || courses.isEmpty()) {
			return new ResponseEntity<Course>(HttpStatus.NO_CONTENT);
		}

		// TODO Throw custom exception
		return new ResponseEntity<Course>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}