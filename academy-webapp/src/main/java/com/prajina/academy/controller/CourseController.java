package com.prajina.academy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.prajina.academy.model.CourseView;
import com.prajina.academy.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseService courseService;
	
	@GetMapping("/view")
	public ModelAndView getCourses(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(required = false, defaultValue = "30") Integer pageSize,
			@RequestParam(required = false, defaultValue = "name") String sortBy) {
		logger.debug("Fetching all courses... pageNum :: " + pageNum + ", pageSize :: " + pageSize + ", sortBy :: "
				+ sortBy);
		ResponseEntity<CourseView[]> responseEntity = courseService.findAll(pageNum, pageSize, sortBy);
		ModelAndView mav = new ModelAndView("course");
		mav.addObject("courseList", responseEntity.getBody());

		return mav;
	}
	
	@GetMapping(value = "")
	public ResponseEntity<?> listAllCourses(
			@RequestParam(required = false, defaultValue="0") Integer pageNum,
			@RequestParam(required = false, defaultValue="100") Integer pageSize,
			@RequestParam(required = false, defaultValue = "name") String sortBy) {
		logger.debug("Fetching all courses... pageNum :: " + pageNum +", pageSize :: " + pageSize + ", sortBy :: " + sortBy);
		ResponseEntity<CourseView[]> responseEntity = courseService.findAll(pageNum, pageSize, sortBy);
		
		return responseEntity;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CourseView> getCourse(@PathVariable("id") Long id) {
		logger.debug("Fetching Course with id " + id);
		ResponseEntity<CourseView> responseEntity = courseService.findById(id);
		if (responseEntity == null) {
			logger.debug("Course with id " + id + " not found");
			return new ResponseEntity<CourseView>(HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<?> createCourse(@RequestBody CourseView course, UriComponentsBuilder ucBuilder) {
		logger.debug("Creating Course " + course.getName());

		return courseService.save(course);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCourse(@PathVariable("id") Long id, @RequestBody CourseView course) {
		logger.debug("Updating Course " + id);
		ResponseEntity<CourseView> responseEntity = courseService.update(id, course);
		
		return responseEntity;
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<CourseView> updatePartialCourse(@PathVariable("id") Long id, @RequestBody CourseView course) {
		logger.debug("Partial pdating Course " + id);

		return courseService.patch(id, course);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteCourseById(@PathVariable("id") Long id) {
		logger.debug("Deleting Course with id " + id);
		return courseService.deleteWithExchange(id);
	}
}
