package com.prajina.academy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.prajina.academy.model.Course;
import com.prajina.academy.service.CourseService;

@Controller
@RequestMapping("/academy/course")
public class CourseController {

	Logger logger = LoggerFactory.getLogger(CourseController.class);

	@Autowired
	private CourseService service;

	@GetMapping("/async")
	public String getCourse() {
		return "courseAsync";
	}

	@GetMapping("")
	public ModelAndView getCourses(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(required = false, defaultValue = "30") Integer pageSize,
			@RequestParam(required = false, defaultValue = "name") String sortBy) {
		logger.debug("Fetching all courses... pageNum :: " + pageNum + ", pageSize :: " + pageSize + ", sortBy :: "
				+ sortBy);
		System.out.println(" \n UI course ctrl get Course  pageNum :: " + pageNum + ", pageSize :: " + pageSize
				+ ", sortBy :: " + sortBy);
		ResponseEntity<Course[]> responseEntity = service.findAll(pageNum, pageSize, sortBy);
		ModelAndView mav = new ModelAndView("courseAngularTag");
		System.out.println(" responseEntity.getBody() :: " + responseEntity.getBody());
		mav.addObject("courseList", responseEntity.getBody());

		return mav;
	}

	@GetMapping("/sync")
	public ModelAndView getCoursesSync(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(required = false, defaultValue = "30") Integer pageSize,
			@RequestParam(required = false, defaultValue = "name") String sortBy) {
		logger.debug("Fetching all courses... pageNum :: " + pageNum + ", pageSize :: " + pageSize + ", sortBy :: "
				+ sortBy);
		System.out.println(" \n UI course ctrl get Course  pageNum :: " + pageNum + ", pageSize :: " + pageSize
				+ ", sortBy :: " + sortBy);
		ResponseEntity<Course[]> responseEntity = service.findAll(pageNum, pageSize, sortBy);
		ModelAndView mav = new ModelAndView("course");
		System.out.println(" responseEntity.getBody() :: " + responseEntity.getBody());
		mav.addObject("courseList", responseEntity.getBody());

		return mav;
	}

	@PostMapping("/save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("course") Course course) {
		System.out.println(" \n UI course ctrl course :: " + course.toString());
		service.save(course);
		System.out.println(" \n UI course ctrl AFTER save :: " + course.toString());
		ModelAndView mav = getCourses(request, response, 0, 100, null);

		return mav;
	}

	@RequestMapping("/courseForm")
	public String showform(Model m) {
		m.addAttribute("command", new Course());
		return "courseForm";
	}

}
