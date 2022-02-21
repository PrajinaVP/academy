package com.prajina.academy.transformer;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.prajina.academy.entity.CourseEntity;
import com.prajina.academy.model.Course;

@Component
public class CourseMapper {
	
	private ModelMapper mapper = new ModelMapper();
	
	public Course toModel(CourseEntity entity) {
		
		return mapper.map(entity, Course.class);
	}
	
	public List<Course> toModel(List<CourseEntity> entityList) {
		return entityList.stream()
				.map(item -> toModel(item))
				.collect(Collectors.toList());
	}
	
	public CourseEntity toEntity(Course model) {
		
		return mapper.map(model, CourseEntity.class);
	}
	
}