package com.prajina.academy.transformer;

import org.springframework.stereotype.Component;

import com.prajina.academy.model.Course;

@Component
public class CourseTransformer {
	
	public Course toModel(com.prajina.academy.entity.Course entity) {
		if (entity == null) {
			throw new RuntimeException("Unable to transorm course entity to course model. Empty entity");
		}
		
		Course model = new Course();
		model.setId(entity.getId());
		model.setName(entity.getName());
		model.setDesc(entity.getDesc());
		model.setContact(entity.getContact());
		
		return model;
	}
	
	public com.prajina.academy.entity.Course toEntity(Course model) {
		if (model == null) {
			throw new RuntimeException("Unable to transorm course entity to course model. Empty entity");
		}
		
		com.prajina.academy.entity.Course entity = new com.prajina.academy.entity.Course();
		model.setId(model.getId());
		model.setName(model.getName());
		model.setDesc(model.getDesc());
		model.setContact(model.getContact());
		
		return entity;
	}

}
