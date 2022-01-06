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
@RequestMapping("/academy/course")
public class CourseController {

	@Autowired
	private CourseService service;

	@GetMapping("/async")
	public String getCourse() {
		return "courseAsync";
	}

	@GetMapping("")
	public ModelAndView getCourses(HttpServletRequest request, HttpServletResponse response) {
		List<Course> courseList = service.findAll();
		ModelAndView mav = new ModelAndView("course");
		mav.addObject("courseList", courseList);

		return mav;
	}

	@PostMapping("/save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("course") Course course) {
		// TODO This should be save/ merge when db is set up
		if (service.isCourseExist(course)) {
			service.update(course);
		} else {
			service.create(course);
		}

		List<Course> courses = service.findAll();
		ModelAndView mav = new ModelAndView("course");
		mav.addObject("courseList", courses);

		return mav;
	}

	@RequestMapping("/courseForm")
	public String showform(Model m) {
		m.addAttribute("command", new Course());
		return "courseForm";
	}

}
