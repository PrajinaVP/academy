package com.prajina.academy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.prajina.academy.model.Course;
import com.prajina.academy.service.CourseService;

@Controller
public class CourseController {

	@Autowired
	CourseService service;

	@GetMapping("/v1/course")
	public String getIndexPage() {
		System.out.println("V1");
		return "courseAsync";
	}

	@GetMapping("/courses")
	public ModelAndView getCourses(HttpServletRequest request, HttpServletResponse response) {
		List<Course> courses = service.findAll();
		ModelAndView mav = new ModelAndView("course");
		mav.addObject("courses", courses);

		return mav;
	}

	@PostMapping("/save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("course") Course course) {
		System.out.println("Course Controller :: save :: " + course.toString());
		/*
		 * if (course == null || course.getName().isEmpty()) { throw new
		 * RuntimeException("Please provide course name"); }
		 */
		// TODO This should be save/ merge when db is set up
		if (service.isCourseExist(course)) {
			service.update(course);
		} else {
			service.create(course);
		}

		List<Course> courses = service.findAll();
		System.out.println("\n\ncourses :: \n" + courses);
		ModelAndView mav = new ModelAndView("course");
		mav.addObject("courses", courses);

		return mav;
	}

	@RequestMapping("/courseForm")
	public String showform(Model m) {
		m.addAttribute("command", new Course());
		return "courseForm";
	}
//	@GetMapping("/courses")
//	public ResponseEntity<List<Course>> findAllCourses() {
//		// TODO Use Optional
//		List<Course> courses = service.findAllCourses();
//		if (courses.isEmpty()) {
//			return new ResponseEntity<List<Course>>(HttpStatus.NO_CONTENT);
//		}
//
//		return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
//	}

}
