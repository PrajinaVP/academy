package com.prajina.academy.transformer;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.prajina.academy.model.Course;

@Component
public class CourseMapper {
	
	private ModelMapper mapper = new ModelMapper();
	
	public Course toModel(com.prajina.academy.entity.Course entity) {
		return mapper.map(entity, Course.class);
	}
	
	public List<Course> toModel(List<com.prajina.academy.entity.Course> entityList) {
		return entityList.stream()
				.map(item -> toModel(item))
				.collect(Collectors.toList());
	}
	
	public com.prajina.academy.entity.Course toEntity(Course model) {
		return mapper.map(model, com.prajina.academy.entity.Course.class);
	}

}
