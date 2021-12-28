package com.prajina.academy.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.prajina.academy.model.Course;

@Service("CourseService")
public class CourseServiceImpl implements CourseService {

	private static final AtomicInteger counter = new AtomicInteger();

	// TODO Read from xls file
	private static List<Course> courses;

	static {
		courses = getCourses();
	}

	@Override
	public List<Course> findAll() {
		return courses;
	}

	@Override
	public Course findById(Integer id) {
		for (Course course : courses) {
			if (course.getId() == id) {
				return course;
			}
		}
		return null;
	}

	@Override
	public Course findByName(String name) {
		for (Course course : courses) {
			if (course.getName().equalsIgnoreCase(name)) {
				return course;
			}
		}

		return null;
	}

	@Override
	public List<Course> findByDesc(String desc) {
		List courseContainingDesc = new ArrayList();

		for (Course course : courses) {
			if (course.getDesc().contains(desc)) {
				courseContainingDesc.add(course);
			}
		}

		return courseContainingDesc;
	}

	@Override
	public Course create(Course course) {
		if (course == null) {
			throw new RuntimeException("No course provided!");
		}
		Integer id = counter.incrementAndGet();
		course.setId(id);
		courses.add(course);

		return courses.get(courses.indexOf(course));
	}

	@Override
	public List<Course> create(List<Course> courseList) {
		if (courseList == null) {
			throw new RuntimeException("No course provided!");
		}

		for (Course course : courseList) {
			course.setId(counter.incrementAndGet());
			courses.add(course);
		}

		return courses;
	}

	@Override
	public Course update(Course course) {
		int index = courses.indexOf(course);
		courses.set(index, course);

		return courses.get(index);
	}

	@Override
	public void updateCourse(List<Course> courseList) {
		for (Course course : courses) {
			int index = courses.indexOf(course);
			courses.set(index, course);
		}
	}

	@Override
	public void deleteCourse(List<Course> courseList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {

		for (Iterator<Course> iterator = courses.iterator(); iterator.hasNext();) {
			Course course = iterator.next();
			if (course.getId() == id) {
				iterator.remove();
			}
		}
	}

	@Override
	public void deleteAll() {
		courses.clear();
	}

	@Override
	public boolean isCourseExist(Course course) {

		return findById(course.getId()) != null;
	}

	private static List<Course> getCourses() {
		List<Course> courses = new ArrayList<Course>();
		courses.add(new Course(counter.incrementAndGet(), "Java", "Java", "active", "java@contact.praj"));
		courses.add(new Course(counter.incrementAndGet(), "Advanced Java", "Basic Java Constructs", "inactive",
				"advjava@contact.praj"));
		courses.add(new Course(counter.incrementAndGet(), "Angular", "Angular JS", "active", "ajs@contact.praj"));

		return courses;
	}
}
