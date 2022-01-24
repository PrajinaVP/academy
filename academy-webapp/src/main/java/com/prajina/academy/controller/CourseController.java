package com.prajina.academy.controller;

import java.util.List;

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
			@RequestParam(required = false, defaultValue="0") Integer pageNum,
			@RequestParam(required = false, defaultValue="30") Integer pageSize,
			@RequestParam(required = false, defaultValue = "name") String sortBy) {
		logger.error("Fetching all courses... pageNum :: " + pageNum +", pageSize :: " + pageSize + ", sortBy :: " + sortBy);
		logger.debug("Fetching all courses...");
		
		ResponseEntity<Course[]> responseEntity = service.findAll(pageNum, pageSize, sortBy);
		ModelAndView mav = new ModelAndView("course");
		mav.addObject("courseList", responseEntity.getBody());

		return mav;
	}

	@PostMapping("/save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("course") Course course) {
		
		service.save(course);
		
		// Use default values
		ModelAndView mav = getCourses(request, response, null, null, null);
//		List<Course> courses = service.findAll();
//		ModelAndView mav = new ModelAndView("course");
//		mav.addObject("courseList", courses);

		return mav;
	}

	@RequestMapping("/courseForm")
	public String showform(Model m) {
		m.addAttribute("command", new Course());
		return "courseForm";
	}

}
