package com.prajina.academy;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.prajina.academy.entity.CourseEntity;
import com.prajina.academy.entity.ModuleEntity;
import com.prajina.academy.model.Course;
import com.prajina.academy.model.Module;

public class TestData {
	
	public CourseEntity getCourseEntity() {
		return new CourseEntity(1L, "Java SE 17", "Java 17", "java@praj.com", "v1", "active", null);
	}
	
	public Course getCourse() {
		return new Course(1L, "Java SE 17", "Java 17", "java@praj.com", "v1", "active", null);
	}
	
	public List<CourseEntity> mockCourseEntityList() {
		CourseEntity course1 = new CourseEntity(1L, "Java SE 7", "Java 7", "java@praj.com", "v1", "inactive", null);
		CourseEntity course2 = new CourseEntity(2L, "Java SE 17", "Java 17", "java@praj.com", "v2", "active", null);
		CourseEntity course3 = new CourseEntity(3L, "Angular", "Angular", "angular@praj.com", "v1", "active", null);
		List<CourseEntity> courses = new ArrayList<CourseEntity>();
		courses.add(course1);
		courses.add(course2);
		courses.add(course3);

		return courses;
	}

	public Page<CourseEntity> mockOnePageCourseData() {
		Page<CourseEntity> result = mock(Page.class);
		doReturn(3).when(result).getNumberOfElements();
		doReturn(mockCourseEntityList()).when(result).getContent();

		return result;
	}
	
	public ModuleEntity getModuleEntity() {
		return new ModuleEntity(1L, "Java Modifiers", "Java Modifiers Module", "java@praj.com", "v1", "active", null);
	}
	
	public Module getModule() {
		return new Module(1L, "Java Modifiers", "Java Modifiers Module", "java@praj.com", "v1", "active", null);
	}
	
	public List<ModuleEntity> mockModuleEntityList() {
		ModuleEntity module1 = new ModuleEntity(1L, "Java SE 7", "Java 7", "java@praj.com", "v1", "inactive", null);
		ModuleEntity module2 = new ModuleEntity(2L, "Java Modifiers", "Java Modifiers Module", "java@praj.com", "v2", "active", null);
		ModuleEntity module3 = new ModuleEntity(3L, "Angular", "Angular", "angular@praj.com", "v1", "active", null);
		List<ModuleEntity> modules = new ArrayList<ModuleEntity>();
		modules.add(module1);
		modules.add(module2);
		modules.add(module3);

		return modules;
	}

	public Page<ModuleEntity> mockOnePageModuleData() {
		Page<ModuleEntity> result = mock(Page.class);
		doReturn(3).when(result).getNumberOfElements();
		doReturn(mockModuleEntityList()).when(result).getContent();

		return result;
	}
}